package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.FAQ;
import org.iesvdm.api_rest.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FAQService {

    @Autowired
    FAQRepository faqRepository;

    public List<FAQ> all(){return this.faqRepository.findAll();}
}
