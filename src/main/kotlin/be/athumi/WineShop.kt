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
        if(wine.name != WINE_BREWED_BY_ALEXANDER_THE_GREAT){
            wine.expiresInYears -= 1
        }
    }


    fun next() {
        // Wine Shop logic
        items.forEach { wine ->

        if (isStandardWine(wine)) {
                decreaseWinePrice(wine)
            } else {
                if (wine.price < 100) {
                   increasePriceIfPossible(wine)

                    if (wine.name.startsWith(EVENT)) {
                        if (wine.expiresInYears < 8) {
                            increasePriceIfPossible(wine)
                        }

                        if (wine.expiresInYears < 3) {
                            increasePriceIfPossible(wine, 2)
                        }
                    }
                }
            }

            updateExpiration(wine)

            if (wine.expiresInYears < 0) {
                if (!wine.name.contains(CONSERVATO)) {
                    if (!wine.name.contains(EVENT)) {
                        decreaseWinePrice(wine)
                    } else {
                        wine.price = wine.price - wine.price
                    }
                } else {
                    if (wine.price < 100) {
                        increasePriceIfPossible(wine)
                    }
                }
            }

            if (wine.price < 0) {
                wine.price = 0
            }
        }
    }


}