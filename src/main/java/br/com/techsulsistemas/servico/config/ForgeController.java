/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.config;

import br.com.techsulsistemas.servico.config.mensagem.MessageDto;
import br.com.techsulsistemas.servico.config.mensagem.FeedBuilder;
import javax.ws.rs.core.Response;

/**
 *
 * @author kelvin
 */
public abstract class ForgeController {

    private final FeedBuilder feedBuilder = new FeedBuilder();
    
    public ForgeController() {
    }

    protected void addData(Object obj) {
        feedBuilder.add(obj);
    }

    protected void addData(String key, Object obj) {
        feedBuilder.add(key, obj);
    }

    protected void addMessage(String mensagem) {
        feedBuilder.add(new MessageDto("success", mensagem));
    }

    protected void addInfo(String mensagem) {
        feedBuilder.add(new MessageDto("info", mensagem));
    }

    protected void addWarning(String mensagem) {
        feedBuilder.add(new MessageDto("warning", mensagem));
    }

    protected void addError(String mensagem) {
        feedBuilder.add(new MessageDto("error", mensagem));
    }

    protected void addError(Exception exception) {
        feedBuilder.add(new MessageDto("error", exception.getLocalizedMessage()));
    }

    protected Response build() {
        return feedBuilder.build();
    }
    
    protected Response build(String mediaType) {
        return feedBuilder.build(mediaType);
    }

}

