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
    fun next() {
        // Wine Shop logic
        for (i in items.indices) {
            if (items[i].name != BOURDEAUX_CONSERVATO && items[i].name != BOURGOGNE_CONSERVATO && !items[i].name.startsWith(EVENT)) {
                if (items[i].price > 0) {
                    if (items[i].name != WINE_BREWED_BY_ALEXANDER_THE_GREAT) {
                        items[i].price = items[i].price - 1
                    }
                }
            } else {
                if (items[i].price < 100) {
                    items[i].price = items[i].price + 1

                    if (items[i].name.startsWith(EVENT)) {
                        if (items[i].expiresInYears < 8) {
                            if (items[i].price < 100) {
                                items[i].price = items[i].price + 1
                            }
                        }

                        if (items[i].expiresInYears < 3) {
                            if (items[i].price < 100) {
                                items[i].price = items[i].price + 2
                            }
                        }
                    }
                }
            }

            if (items[i].name != WINE_BREWED_BY_ALEXANDER_THE_GREAT) {
                items[i].expiresInYears = items[i].expiresInYears - 1
            } else if (items[i].price < 0) {
                items[i].price = 0
            }

            if (items[i].expiresInYears < 0) {
                if (!items[i].name.contains(CONSERVATO)) {
                    if (!items[i].name.contains(EVENT)) {
                        if (items[i].price > 0) {
                            if (items[i].name != WINE_BREWED_BY_ALEXANDER_THE_GREAT) {
                                items[i].price = items[i].price - 1
                            }
                        }
                    } else {
                        items[i].price = items[i].price - items[i].price
                    }
                } else {
                    if (items[i].price < 100) {
                        items[i].price = items[i].price + 1
                    }
                }
            }

            if (items[i].price < 0) {
                items[i].price = 0
            }
        }
    }
}