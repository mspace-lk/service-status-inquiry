package hms.tap.servicestatusinquiry.controller;

import hms.tap.servicestatusinquiry.domain.Document;
import hms.tap.servicestatusinquiry.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class DocumentDetailController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("document", documentService.findAllDocumentRequest());
        model.addAttribute("Date", new Document().getLastUpdateDate());
        return "documentRequestDetails";
    }

    @RequestMapping(path = "/create/request", method = RequestMethod.GET)
    public String update(Model model) {
        model.addAttribute("document", new Document());
        return "saveDocumentDetails";
    }

    @RequestMapping(value = "/create/request", method = RequestMethod.POST)
    public String save(@ModelAttribute("document") Document documentForm,
                       Model model) {
        documentService.createDocumentRequest(documentForm);
        model.addAttribute("document", documentService.findAllDocumentRequest());
        return "success";
    }

    @RequestMapping(path = "/update/request/{refNo}", method = RequestMethod.POST)
    @ResponseBody
    public Document update(@PathVariable(name = "refNo") String refNumber,
                           @ModelAttribute("documentForm") Document document) {
        return documentService.updateDocumentRequestByRefNumber(refNumber, document);
    }
}
