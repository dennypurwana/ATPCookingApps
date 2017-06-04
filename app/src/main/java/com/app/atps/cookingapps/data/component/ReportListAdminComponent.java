package com.app.atps.cookingapps.data.component;

import com.app.atps.cookingapps.data.module.ListOrderGuestActivityModule;
import com.app.atps.cookingapps.data.module.ReportListAdminModule;
import com.app.atps.cookingapps.util.CustomScope;
import com.app.atps.cookingapps.view.ListOrderGuestActivity;
import com.app.atps.cookingapps.view.ReportListAdminActivity;

import dagger.Component;

/**
 * Created by emerio on 5/25/17.
 */

@CustomScope
@Component(dependencies = NetComponent.class,modules = ReportListAdminModule.class)
public interface ReportListAdminComponent {
    void inject(ReportListAdminActivity reportListAdminActivity);
}
