package com.kor.foodmanager.ui.eventList;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.kor.foodmanager.data.model.FiltersDto;
import com.kor.foodmanager.data.model.SpinnerPositionDto;
import com.kor.foodmanager.data.model.StaticfieldsDto;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface IFilters extends MvpView {
    void setStaticFields(StaticfieldsDto staticFields);
    void setDates(FiltersDto filters);
    void showProgressFrame();
    void hideProgressFrame();
    void setSpinners(SpinnerPositionDto spinnerPositions);
}
