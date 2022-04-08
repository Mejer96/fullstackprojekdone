package com.example.projekt2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class indexController {

    Repository repository = new Repository();
    UserService userService = new UserService(repository);
    WishlistService wishlistService = new WishlistService(repository);

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model){
        model.addAttribute("user", httpSession.getAttribute("user"));

        return "index";
    }

    @GetMapping("/show-wishlists")
    public String showWishlists(Model model, HttpSession httpSession) throws SQLException {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("wishlists", wishlistService.getWishlist(user.getUser_ID()));
        model.addAttribute("user", user);
        return "wishlists";
    }

    @GetMapping("/show-wishlist-items/{wishlist-id}")
    public String showWishlistItems(@PathVariable("wishlist-id") String wishlistID, Model model, HttpSession httpSession) throws SQLException {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("wishlistItems", wishlistService.getWishlistItems(wishlistID));
        model.addAttribute("user", user);
        model.addAttribute("wishlistID", wishlistID);

        return "wishlist-items";
    }

    @GetMapping("/delete-wishlist/{wishlistID}")
    public String deleteWishlist(@PathVariable("wishlistID") String id, Model model) throws SQLException {
        wishlistService.deleteWishlist(id);
        return "redirect:/show-wishlists";
    }


    @PostMapping("/create-wishlist")
    // gennem en HTML form kan vi hente user input fra vores hjemmeside og sende dem videre til vores backend.
    public String getWishlist(@RequestParam("name") String name, @RequestParam("description") String description, Model model, HttpSession httpSession) throws SQLException {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        wishlistService.createWishlist(name, description, user.getUser_ID());
        return "redirect:/show-wishlists";
    }

    @PostMapping("/create-wish/{wishlist_ID}")
    public String postWish(@PathVariable("wishlist_ID") String wishlist_ID, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("price") String price, HttpSession session, Model model) throws SQLException {
        repository.createWish(name, price, description, wishlist_ID);
        return "redirect:/show-wishlist-items/" + wishlist_ID;
    }

    @GetMapping("/create-wish/{wishlistID}")
    public String createWish(@PathVariable("wishlistID") String wishlistID, Model model) {
        model.addAttribute("wishlistID", wishlistID);

        return "create-wish";
    }

    @PostMapping("/join")
    public String createUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) throws SQLException {
        userService.createUser(username, password);
        User user = userService.userLogin(username, password);
        session.setAttribute("user", user);

        return "redirect:/show-wishlists";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("/delete-wish/{wishID}/{wishlistID}")
    public String deleteWish(@PathVariable("wishID") String id, @PathVariable("wishlistID") String wishlistID) throws SQLException {
        wishlistService.deleteWish(id);

        return "redirect:/show-wishlist-items/" + wishlistID;
    }

    @GetMapping("create-wishlist")
    public String createWishlist(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        return "create-wishlist";
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String getLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession) throws SQLException {
        User user = userService.userLogin(username, password);
        httpSession.setAttribute("user", user);

        return "redirect:/show-wishlists";
    }

    @GetMapping("/error-no-login")
    public String errorNoLogin() {
        return "error-no-login";
    }

    @GetMapping("/error-username")
    public String errorUsername() {
        return "error-username";
    }

    @GetMapping("/log-out")
    public String logOut(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:/";
    }

}
