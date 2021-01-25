package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.MicroFirm.repository.TransItemRepository;

@Controller
@RequestMapping(path = "/transItem")
public class TransItemController {

    private final TransItemRepository transItemRepository;
    public TransItemController(TransItemRepository transItemRepository) {
        this.transItemRepository = transItemRepository;
    }



}
