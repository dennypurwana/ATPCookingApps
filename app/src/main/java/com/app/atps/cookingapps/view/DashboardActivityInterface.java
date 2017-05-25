package com.app.atps.cookingapps.view;

/**
 * Created by emerio on 5/25/17.
 */

public interface DashboardActivityInterface  {
    interface View{
            void toListMenuMakananPage();
            void toUsersPage();
            void toTableRestaurantPage();
            void toReportSummaryPage();
            void toPromotionPage();
            void toListNotificationPage();
    }
    interface Presenter{

    }
}
