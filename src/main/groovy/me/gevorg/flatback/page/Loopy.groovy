package me.gevorg.flatback.page

import geb.Page

/**
 * Loopy page.
 *
 * @author Gevorg Harutyunyan
 */
class Loopy extends Page {
    String pattern
    String definition

    @Override
    String getPageUrl() {
        String pattern = URLEncoder.encode(this.pattern, 'UTF-8')
        String definition = URLEncoder.encode(this.definition, 'UTF-8')

        return "http://loopy.ru/?word=${pattern}&def=${definition}"
    }

    /**
     * Returns list of hints.
     *
     * @return list of hints.
     */
    List<String> getHints() {
        List<String> result = []

        $("div.wd > h3 > a").each {
            result << it.text()
        }

        return result
    }
}
