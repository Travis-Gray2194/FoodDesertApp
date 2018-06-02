package me.travisgray.DataLoader;

import me.travisgray.Models.Item;
import me.travisgray.Models.Role;
import me.travisgray.Models.User;
import me.travisgray.Repositories.ItemRepository;
import me.travisgray.Repositories.RoleRepository;
import me.travisgray.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        // Add user roles
        User user1 = new User("bob@burger.com", "password", "Bobby", "Burger", true, "bob");
        user1.setRoles(Arrays.asList(userRole));
        userRepository.save(user1);

        User user2 = new User("jane@virgin.com", "password", "Jane", "Virgin", true, "jane");
        user2.setRoles(Arrays.asList(userRole));
        userRepository.save(user2);

        // Add admin roles
        User user3 = new User("admin@secure.com", "password", "Admin", "User", true, "admin");
        user3.setRoles(Arrays.asList(adminRole));
        userRepository.save(user3);

        User user4 = new User("clark@kent.com", "password", "Clark", "Kent", true, "clark");
        user4.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(user4);


        Item item6 = new Item("https://assets.epicurious.com/photos/590a09c0246f17645fc62139/2:1/w_1260%2Ch_630/Ground_Beef_Craft_Paper_03052017.jpg","Ground Beef","5","Food" );
        itemRepository.save(item6);

        Item item1 = new Item("https://cdn-image.foodandwine.com/sites/default/files/styles/medium_2x/public/201408-xl-lemon-thyme-roast-chicken.jpg?itok=Q6Mx3Te2","Chicken","5","Drink" );
        itemRepository.save(item1);

        Item item2 = new Item("https://www.pepperscale.com/wp-content/uploads/2016/01/bell-pepper-substitute.jpg","Pepper","5","Food" );
        itemRepository.save(item2);

        Item item3 = new Item("http://bcfresh.ca/wp-content/uploads/2016/03/carrots.jpg","Carrots","5","Food" );
        itemRepository.save(item3);

        Item item4 = new Item("http://dreamatico.com/data_images/apple/apple-8.jpg","Apple","10","Food" );
        itemRepository.save(item4);

        Item item5 = new Item("https://vignette.wikia.nocookie.net/deathbattle/images/4/4c/Milk-carton-thumbnail.jpg/revision/latest/scale-to-width-down/480?cb=20170402204752","Milk","10","Food" );
        itemRepository.save(item5);


        Item item7 = new Item("https://vignette.wikia.nocookie.net/epicrapbattlesofhistory/images/c/c7/Banana.jpg/revision/latest?cb=20140911204945","Banana","10","Food" );
        itemRepository.save(item7);

        Item item8 = new Item("https://static1.squarespace.com/static/57b72c3cff7c50cc4363be14/t/5a446a83e4966b67c56a8696/1514433727321/eggs.jpg?format=750w","Eggs","10","Food" );
        itemRepository.save(item8);

        Item item9 = new Item("http://store.foodforhealthinternational.com/images/products/4599.jpg","Rice","10","Food" );
        itemRepository.save(item9);

        Item item10 = new Item("https://upload.wikimedia.org/wikipedia/commons/b/bc/A_field_of_wheat.JPG","Wheat","10","Food" );
        itemRepository.save(item10);
    }
}