package org.poo.MyProject.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.poo.utils.Utils;

@Data
public class Card {
    private String cardNumber;
    private String status;
    @JsonIgnore
    private boolean oneTimeCard = false;
    public Card(final String cardNumber, final String status) {
        this.cardNumber = cardNumber;
        this.status = status;
    }
    public Card(final Card card) {
        cardNumber = card.getCardNumber();
        status = card.getStatus();
    }
    public Card() {
        cardNumber = Utils.generateCardNumber();
        status = "active";
    }
    /**
     * @param oneTimeCard trebuie sa fie 'true' pentru ca cardul sa fie de unica folosinta
     */
    public Card(final boolean oneTimeCard) {
        cardNumber = Utils.generateCardNumber();
        status = "active";
        this.oneTimeCard = true;
    }
}
