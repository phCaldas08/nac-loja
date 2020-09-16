package br.com.fiap.nacloja.business;

import br.com.fiap.nacloja.exception.business.UrlFormatException;
import br.com.fiap.nacloja.model.LojaModel;
import br.com.fiap.nacloja.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LojaBusiness {

    @Autowired
    private LojaRepository repository;

    public LojaModel save(LojaModel lojaModel) throws Exception {
        lojaModel = applyBusiness(lojaModel);

        return repository.save(lojaModel);
    }

    private LojaModel applyBusiness(LojaModel lojaModel) throws Exception {
        lojaModel.setUrl(formatUrl(lojaModel.getUrl()));
        validUrl(lojaModel.getUrl());

        return lojaModel;
    }

    private String formatUrl(String url){
        return url.toLowerCase();
    }



    private void validUrl(String url) throws Exception {
        String _url = url.toLowerCase();

        if(!_url.startsWith("http://www") && !_url.startsWith("https://www")) {
            throw new UrlFormatException();
        }
    }



}
