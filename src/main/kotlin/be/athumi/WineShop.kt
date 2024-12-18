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


    fun next() {
        // Wine Shop logic
        for (i in items.indices) {
            if (isStandardWine(items[i])) {
                decreaseWinePrice(items[i])
            } else {
                if (items[i].price < 100) {
                    items[i].price = items[i].price + 1

                    if (items[i].name.startsWith(EVENT)) {
                        if (items[i].expiresInYears < 8) {
                            increasePriceIfPossible(items[i])
                        }

                        if (items[i].expiresInYears < 3) {

                            increasePriceIfPossible(items[i], 2)

                        }
                    }
                }
            }

            if (isNotBrewedByAlexander(items[i])) {
                items[i].expiresInYears = items[i].expiresInYears - 1
            } else if (items[i].price < 0) {
                items[i].price = 0
            }

            if (items[i].expiresInYears < 0) {
                if (!items[i].name.contains(CONSERVATO)) {
                    if (!items[i].name.contains(EVENT)) {
                        decreaseWinePrice(items[i])
                    } else {
                        items[i].price = items[i].price - items[i].price
                    }
                } else {
                    if (items[i].price < 100) {
                        increasePriceIfPossible(items[i])
                    }
                }
            }

            if (items[i].price < 0) {
                items[i].price = 0
            }
        }
    }


}