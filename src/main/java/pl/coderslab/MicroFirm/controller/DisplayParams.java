package pl.coderslab.MicroFirm.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class DisplayParams {

    private final static String DISABLED_PARAM = "disabledParam";
    private final static String SUBMIT_BTN_VISIBLE_PARAM = "submitBtnVisibleParam";
    private final static String EDIT_BTN_VISIBLE_PARAM = "editBtnVisibleParam";
    private final static String DEL_BTN_VISIBLE_PARAM = "delBtnVisibleParam";
    private final static String ADD_TR_ITEM_BTN_VISIBLE_PARAM = "addTransItemBtnVisibleParam";
    private final static String TRUE = "true";
    private final static String FALSE = "false";
    private final static String INVISIBLE = "invisible";
    private final static String VISIBLE = "visible";

    public void setShowParams(Model model){
        model.addAttribute(DISABLED_PARAM, TRUE);
        model.addAttribute(SUBMIT_BTN_VISIBLE_PARAM, INVISIBLE);
        model.addAttribute(EDIT_BTN_VISIBLE_PARAM, VISIBLE);
        model.addAttribute(DEL_BTN_VISIBLE_PARAM, VISIBLE);
        model.addAttribute(ADD_TR_ITEM_BTN_VISIBLE_PARAM, VISIBLE);
    }

    public void setAddEditParams(Model model){
        model.addAttribute(DISABLED_PARAM, FALSE);
        model.addAttribute(SUBMIT_BTN_VISIBLE_PARAM, VISIBLE);
        model.addAttribute(EDIT_BTN_VISIBLE_PARAM, INVISIBLE);
        model.addAttribute(DEL_BTN_VISIBLE_PARAM, INVISIBLE);
        model.addAttribute(ADD_TR_ITEM_BTN_VISIBLE_PARAM, INVISIBLE);
    }

    public void setDelParams(Model model){
        model.addAttribute(DISABLED_PARAM, TRUE);
        model.addAttribute(SUBMIT_BTN_VISIBLE_PARAM, VISIBLE);
        model.addAttribute(EDIT_BTN_VISIBLE_PARAM, INVISIBLE);
        model.addAttribute(DEL_BTN_VISIBLE_PARAM, INVISIBLE);
        model.addAttribute(ADD_TR_ITEM_BTN_VISIBLE_PARAM, INVISIBLE);
    }
}