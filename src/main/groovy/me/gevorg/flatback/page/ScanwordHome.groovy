package me.gevorg.flatback.page

import geb.Page

/**
 * Page for scanword.ru crossword.
 *
 * @author Gevorg Harutyunyan
 */
class ScanwordHome extends Page {
    static url = "https://scanword.ru"
    static at = { title == "Сканворд.ру" }

    static content = {
        username { $('[name=USER_LOGIN]') }
        password { $('[name=USER_PASSWORD]') }
        loginBtn { $('[name=Login]') }
    }
}
