package me.travisgray.DataLoader;

import me.travisgray.Models.Item;
import me.travisgray.Models.Role;
import me.travisgray.Models.User;
import me.travisgray.Repositories.ItemRepository;
import me.travisgray.Repositories.RoleRepository;
import me.travisgray.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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


        Item item1 = new Item("http://media.buzzle.com/media/images-en/gallery/conceptual/600-114334522-fruit-punch-in-glasses.jpg","Fruit Punch","5","Drink" );
        itemRepository.save(item1);

        Item item2 = new Item("http://a0.fanbread.com/uploads/image/file/42469/extra_large_jerk-chicken-940.jpg?fd6a071b7412f614090e924c651ac7af","Jerk Chicken","5","Food" );
        itemRepository.save(item2);

        Item item3 = new Item("http://www.jamesandeverett.com/whatscooking/wp-content/uploads/2012/11/Tuaca-hot-apple-pie-3.jpg","Apple Pie","5","Desert" );
        itemRepository.save(item3);

        Item item4 = new Item("https://holisticwellness.ca/wp-content/uploads/2015/07/chocolate-ice-cream.gif","Choclate Ice Cream","10","Desert" );
        itemRepository.save(item4);

        Item item5 = new Item("https://i1.wp.com/www.nigerianlazychef.com/wp-content/uploads/2015/07/IMG_6605-2.jpg","Jollof Rice","10","Food" );
        itemRepository.save(item5);
    }
}