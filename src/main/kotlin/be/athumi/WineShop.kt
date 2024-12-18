package be.athumi

private const val BOURDEAUX_CONSERVATO = "Bourdeaux Conservato";

private const val BOURGOGNE_CONSERVATO = "Bourgogne Conservato"

private const val EVENT = "Event"

private const val WINE_BREWED_BY_ALEXANDER_THE_GREAT = "Wine brewed by Alexander the Great"

private const val CONSERVATO = "Conservato"

private const val MAX_PRICE = 100

private const val MIN_PRICE = 0

private const val ECO_BRILLIANT_WINE = "Eco Brilliant Wine"

class WineShop(var items: List<Wine>) {

    //check if the wine is standard wine
    private fun isStandardWine(wine: Wine): Boolean {
        return wine.name != BOURDEAUX_CONSERVATO &&
                wine.name != BOURGOGNE_CONSERVATO &&
                !wine.name.startsWith(EVENT)
    }

    //check if wine brewed by Alexander the Great
    private fun isNotBrewedByAlexander(wine: Wine): Boolean {
        return wine.name != WINE_BREWED_BY_ALEXANDER_THE_GREAT
    }


    //decrease the wine price by 1 if greater than min price
    private fun decreaseWinePrice(wine: Wine) {
        if (wine.price > 0) {
            if (isNotBrewedByAlexander(wine)) {
                wine.price -= 1
            }
        }
    }

    /**
     * increase the price of wine by given value (default is 1)
     * the price not exceed the maximum allowed price
     */
    private fun increasePriceIfPossible(wine: Wine, by: Int = 1) {
        if (wine.price < MAX_PRICE) {
            wine.price += by
        }
    }

    //Decrease the expires years by 1 if not brewed by Alexander the Great
    private fun updateExpiration(wine: Wine) {
        if (wine.name != WINE_BREWED_BY_ALEXANDER_THE_GREAT) {
            wine.expiresInYears -= 1
        }
    }

    /**
     * apply the specific price for Event wine
     * the price faster increase IF it is closer to expiration date
     */
    private fun handleEventWine(wine: Wine) {
        increasePriceIfPossible(wine)

        if (wine.expiresInYears < 8) {
            increasePriceIfPossible(wine)
        }
        if (wine.expiresInYears < 3) {
            increasePriceIfPossible(wine, 2)
        }

    }

    //handle Special wine
    private fun handleSpecialWine(wine: Wine) {
        when {
            wine.name.startsWith(EVENT) -> handleEventWine(wine)
            else -> increasePriceIfPossible(wine)
        }

    }

    //handle the wine price after expired
    private fun handleExpiration(wine: Wine) {
        when {
            wine.expiresInYears >= MIN_PRICE -> return
            wine.name.contains(CONSERVATO) -> increasePriceIfPossible(wine)
            wine.name.contains(EVENT) -> wine.price = MIN_PRICE
            else -> if (wine.price > MIN_PRICE) wine.price -= 1
        }
    }

    //when wine name not bewered by Alexander the Great then price
    private fun handlePriceLimits(wine: Wine) {
        if(wine.name != WINE_BREWED_BY_ALEXANDER_THE_GREAT) {
            wine.price = wine.price.coerceIn(MIN_PRICE, MAX_PRICE)
        }
    }

    fun next() {
        // Wine Shop logic
        items.forEach { wine ->

            when{
                isStandardWine(wine) -> decreaseWinePrice(wine)
                else -> handleSpecialWine(wine)
            }
            updateExpiration(wine)
            handleExpiration(wine)
            handlePriceLimits(wine)
        }
    }




}