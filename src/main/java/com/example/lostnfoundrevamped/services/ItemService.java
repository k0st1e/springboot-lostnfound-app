package com.example.lostnfoundrevamped.services;

import com.example.lostnfoundrevamped.entities.CategoryEntity;
import com.example.lostnfoundrevamped.entities.ItemEntity;
import com.example.lostnfoundrevamped.entities.UserEntity;
import com.example.lostnfoundrevamped.entities.dto.CategoriesDto;
import com.example.lostnfoundrevamped.entities.dto.InsertItemDto;
import com.example.lostnfoundrevamped.entities.dto.UserDto;
import com.example.lostnfoundrevamped.repositories.CategoryRepository;
import com.example.lostnfoundrevamped.repositories.ItemRepository;
import com.example.lostnfoundrevamped.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    HttpSession session;

    // Functionality
    // 1 -> Set Entities fields from dto data.
    // 2 -> Time is set with `Instant`.
    // 3 -> Set the blob with `getBytes`.
    // 4 -> Satisfy constraints for user and category...
    // ...with session usage and category id's respectively.
    // TODO: This method does a lot of things it would be better if it was split, SRP comes to mind?
    public ItemEntity convertDtoAndFileToEntity(InsertItemDto dto,
                                                MultipartFile file) {

        ItemEntity item = new ItemEntity();

        // Set basic info from the dto.
        // Item status -> ENUM.
        item.setItemname(dto.getItemname());
        item.setItemdescription(dto.getItemdescription());
        item.setItemlocation(dto.getItemlocation());
        item.setItemcontact(dto.getItemcontact());
        item.setItemstatus(dto.getItemstatus());

        item.setItemdate(Instant.now());

        // Image related.
        try {
            item.setFileblob(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get the current user using session.
        UserDto loggedInUser = (UserDto) session.getAttribute("loggedinuser");
        String username = loggedInUser.getUsername();
        UserEntity user = userRepository.findByUsername(username);
        item.setUserEntity(user);

        // Sets the category.
        CategoryEntity categoryEntity = categoryRepository.
                findByCategoryid(dto.getCategoryId());
        item.setCategoryEntity(categoryEntity);

        return item;
    }

    public void saveItem(ItemEntity item) { itemRepository.save(item); }

    public List<ItemEntity> getAllItems() { return itemRepository.findAll(); }

    public ItemEntity getByItemid(int itemid) {
        return itemRepository.findById(itemid).orElseThrow();
    }

    public List<CategoriesDto> getItemCountsPerCategory() {
        return itemRepository.countItemsByCategory();
    }
}