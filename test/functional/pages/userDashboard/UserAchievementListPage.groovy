package pages.userDashboard

import geb.Page


class UserAchievementListPage extends Page {
    static url = "userDashboard/userAchievementList"

    static at = {
        title ==~ /Merit Badges/
    }

    static content = {
    }
}