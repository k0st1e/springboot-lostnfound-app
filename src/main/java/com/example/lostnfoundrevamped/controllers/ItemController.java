package com.example.lostnfoundrevamped.controllers;

import com.example.lostnfoundrevamped.entities.CategoryEntity;
import com.example.lostnfoundrevamped.entities.ItemEntity;
import com.example.lostnfoundrevamped.entities.dto.InsertItemDto;
import com.example.lostnfoundrevamped.entities.dto.NewAlertDto;
import com.example.lostnfoundrevamped.entities.enums.ItemStatus;
import com.example.lostnfoundrevamped.services.AlertService;
import com.example.lostnfoundrevamped.services.CategoryService;
import com.example.lostnfoundrevamped.services.ItemService;
import com.example.lostnfoundrevamped.services.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MessageService messageService;
    @Autowired
    AlertService alertService;

    // Submit Item
    // Get all categories from `categorySevice` to be able to choose one of them.
    @GetMapping("/submititem")
    public String getSubmitItem(HttpSession session,
                                ModelMap modelMap) {
        if (session.getAttribute("loggedinuser") == null) {
            return "redirect:/";
        }
        modelMap.addAttribute("categories", categoryService.getAllCategories());
        return "submititem";
    }

    // Submit an item when user has chosen either `LOST` or `FOUND` card.
    @PostMapping("/doitemsubmit")
    public String doItemSubmit(@ModelAttribute InsertItemDto dto,
                               @RequestParam("fileblob") MultipartFile file, // fileblob is for image.
                               @RequestParam("status") ItemStatus status, // status is the card weird thingy.
                               @RequestParam("categoryId") Integer itemCat,
                               ModelMap modelMap,
                               NewAlertDto alertDto) {

        // Check if fields are missing
        if (dto.getItemname().isEmpty()
                || dto.getItemdescription().isEmpty()
                || dto.getItemlocation().isEmpty()
                || dto.getItemcontact().isEmpty()) {
            modelMap.addAttribute("message", "Please fill all the fields.");
            return "/submititem";
        }

        // Alerts.
        alertDto.setItemStatus(String.valueOf(status));
        // This here is the category that we use for the logic to FIND users.
        alertDto.setItemCategory(itemCat);
        // Set the dto's alert message depending on the category.
        CategoryEntity categoryEntity = categoryService.getCategoryById(itemCat);
        String category = categoryEntity.getCategoryname();
        alertDto.setAlertMessage("Alert! A new item of category " + category + " has surfaced!");
        // Create the alerts needed.
        alertService.createAlertsForUsers(alertDto);

        // We got the status param since we chose a card so now we set it in the dto
        dto.setItemstatus(status);

        // Call the service to convert and save
        ItemEntity itemEntity = itemService.convertDtoAndFileToEntity(dto, file);
        itemService.saveItem(itemEntity);

        // Redirects to a mapping of /item/ + {itemid}
        // `{itemid}` is covered from `@GetMapping("/item/{itemid}")`
        return "redirect:/item/" + itemEntity.getItemid();
    }

    // - Get the blobs to show them at home!
    // - From Thymeleaf:
    // - th:src="@{/item/image/{id}(id=${item.itemid})}"
    // - /item/image/{itemid} -> `@PathVariable` of type int `itemid`.
    // - Using `itemid` leverage the service `itemService` to get an item by the id.
    // - `ResponseEntity` represents the whole HTTP response(Code, headers & body).
    // -  An entity of type `<byte[]>`, a byte array.
    // - `.ok()` sets the status which is 200.
    // - `.body()` is the content.
    // - Inside the body we get an item with the path variable...
    // - then we find the item with `.getByItemid(itemid)`...
    // - lastly we fetch the BLOOOOOB!!
    @GetMapping("/item/image/{itemid}")
    public ResponseEntity<byte[]> getItemImage(@PathVariable int itemid,
                                               HttpSession session) {
        if (session.getAttribute("loggedinuser") == null) {
            return null; // dwellers can enjoy null
        }
        return ResponseEntity
                .ok()
                .body(itemService.getByItemid(itemid).getFileblob());
    }

    // - Get Item Details and Messages(Submit and History).
    // - From Thymeleaf:
    // - th:href="@{/item/{id}(id=${item.itemid})}"
    // - /item/{itemid} -> `@PathVariable` of type int `itemid`.
    // - Using `itemid` leverage `.getByItemid` of `itemService`.
    // - Add with mm (item details + messages for that item) and return `itemdetails` :) .
    @GetMapping("/item/{itemid}")
    public String getItemDetails(@PathVariable int itemid,
                                 ModelMap modelMap,
                                 HttpSession session) {
        if (session.getAttribute("loggedinuser") == null) {
            return "redirect:/";
        }
        ItemEntity itemEntity = itemService.getByItemid(itemid);
        modelMap.addAttribute("item", itemEntity);
        modelMap.addAttribute("allMessages", messageService.getAllItemsById(itemid));
        return "itemdetails";
    }
}