package com.example.ToDoApp.controller;

import com.example.ToDoApp.model.ToDo;
import com.example.ToDoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/viewToDoList")
    public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", toDoService.getAllToDoItems());
        model.addAttribute("message", message);

        return "ViewToDoList.jsp";
    }

    @PostMapping("/updateToDoStatus/{id}")
    public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (toDoService.updateStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Update Successfully");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Update Failure");
        return "redirect:/viewToDoList";
    }

    @GetMapping("/addToDoItem")
    public String addToDoItem(Model model) {
        model.addAttribute("todo", new ToDo());

        return "AddToDoItem";
    }

    @PostMapping("/saveToDoItem")
    public String saveToDoItem(ToDo toDo, RedirectAttributes redirectAttributes) {
        if (toDoService.saveUpdateToDoItem(toDo)) {
            redirectAttributes.addFlashAttribute("message", "Save Successfully");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addToDoItem";
    }

    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id, Model model) {
        model.addAttribute("todo", toDoService.getToDoItemById(id));
        return "EditToDoItem";
    }

    @PostMapping("/editSaveToDoItem")
    public String editSaveToDoItem(ToDo toDo, RedirectAttributes redirectAttributes) {
        if (toDoService.saveUpdateToDoItem(toDo)) {
            redirectAttributes.addFlashAttribute("message", "Edit Successfully");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editToDoItem/" + toDo.getId();
    }

    @GetMapping("/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (toDoService.deleteToDoItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully");
        }
        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewToDoList";
    }
}
