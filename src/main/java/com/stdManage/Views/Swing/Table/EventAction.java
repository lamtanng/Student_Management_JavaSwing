package com.stdManage.Views.Swing.Table;

import com.stdManage.Models.ModelStudent;

public interface EventAction {

    public void delete(ModelStudent student);

    public void update(ModelStudent student);
}
