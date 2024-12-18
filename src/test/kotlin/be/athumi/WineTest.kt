package be.athumi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WineTest {
    @Test
    fun `tasting or testing wine`() {
        assertThat(Wine("name", 0, 0)).isNotNull
    }

    @Test
    fun `a shop without wine is no shop, is it`() {
        val shop = WineShop(listOf(Wine("name", 0, 0)))

        assertThat(shop).isNotNull

        shop.next()

        assertThat(shop).isNotNull
    }

    @Test
    fun `check Price never exceeds 100 euro`() {
        val shop = WineShop(listOf(Wine("Bourdeaux Conservato", 99, 1),
                Wine("Event Wine", 98, 2)))

        assertThat(shop).isNotNull
        shop.next()
        assertEquals(100, shop.items[0].price)
        assertEquals(100, shop.items[1].price)
    }

    @Test
    fun `Wine brewed by Alexander the great never changes when price greater than 100`() {
        val shop = WineShop(listOf(Wine("Wine brewed by Alexander the Great", 150, 0)))

        shop.next()
        assertEquals(0, shop.items[0].expiresInYears)
        assertEquals(150, shop.items[0].price)
    }

    @Test
    fun `Wine brewed by Alexander the great never changes when price less than 100`() {
        val shop = WineShop(listOf(Wine("Wine brewed by Alexander the Great", 80, 10)))

        shop.next()
        assertEquals(10, shop.items[0].expiresInYears)
        assertEquals(80, shop.items[0].price)
    }

    @Test
    fun `standard wine degrades as expected`() {
        val shop = WineShop(listOf(Wine("Standard Wine", 20, 10)))

        shop.next()
        assertEquals(9, shop.items[0].expiresInYears)
        assertEquals(19, shop.items[0].price)
    }

    @Test
    fun `standard another wine`() {
        val shop = WineShop(listOf(Wine("Another Standard Wine", 7, 5)))

        shop.next()
        assertEquals(4, shop.items[0].expiresInYears)
        assertEquals(6, shop.items[0].price)
    }

    @Test
    fun `check Bourdeaux Conservato wine`() {
        val shop = WineShop(listOf(Wine("Bourdeaux Conservato", 0, 2)))

        shop.next()
        assertEquals(1, shop.items[0].expiresInYears)
        assertEquals(1, shop.items[0].price)
    }

    @Test
    fun `check Event Wine`() {
        val shop = WineShop(listOf(Wine("Event Wine", 20, 15)))

        shop.next()
        assertEquals(14, shop.items[0].expiresInYears)
        assertEquals(21, shop.items[0].price)
    }

    @Test
    fun `check Event Wine when expires year equals 10`() {
        val shop = WineShop(listOf(Wine("Event Wine", 49, 10)))

        shop.next()
        assertEquals(9, shop.items[0].expiresInYears)
        assertEquals(50, shop.items[0].price)
    }

    @Test
    fun `check Event Wine when expires year less than 10`() {
        val shop = WineShop(listOf(Wine("Event Wine", 49, 5)))

        shop.next()
        assertEquals(4, shop.items[0].expiresInYears)
        assertEquals(51, shop.items[0].price)
    }

    @Test
    fun `check Eco Brilliant Wine`() {
        val shop = WineShop(listOf(Wine("Eco Brilliant Wine", 6, 3)))

        shop.next()
        assertEquals(2, shop.items[0].expiresInYears)
        assertEquals(4, shop.items[0].price)
    }

}