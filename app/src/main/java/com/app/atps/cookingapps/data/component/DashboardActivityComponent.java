package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.DashboardActivityModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.DashboardActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = DashboardActivityModule.class)
public interface DashboardActivityComponent {
    void inject(DashboardActivity dashboardActivity);
}
