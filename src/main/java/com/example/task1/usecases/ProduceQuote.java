package com.example.task1.usecases;

import com.example.task1.services.QuoteProducer;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class ProduceQuote implements Serializable {
    @Inject
    QuoteProducer quoteProducer;

    private CompletableFuture<String> quoteProducerTask = null;

    public String produceQuote() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        quoteProducerTask = CompletableFuture.supplyAsync(() -> quoteProducer.produceQuote());

        return  "/personDetails.xhtml?faces-redirect=true&personId=" + requestParameters.get("personId");
    }

    public boolean isQuoteProducerRunning() {
        return quoteProducerTask != null && !quoteProducerTask.isDone();
    }

    public String getQuoteProductionStatus() throws ExecutionException, InterruptedException {
        if (quoteProducerTask == null) {
            return null;
        } else if (isQuoteProducerRunning()) {
            return "Quote production in progress";
        }
        return quoteProducerTask.get();
    }
}
