package pages.userDashboard

import geb.Page


class UserDashboardPage extends Page {
    static url = "userDashboard"

    static at = {
        title ==~ /Merit Badges - Dashboard/
    }

    static content = {
    }
}