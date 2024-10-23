package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class LoggedInPresenter implements ChangePasswordOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // TODO update the viewmodel!
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(outputData.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        loggedInViewModel.firePropertyChanged("password");
    }

    @Override
    public void prepareFailView(String error) {
        // TODO update the viewmodel!
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setPasswordError(error);
        loggedInViewModel.firePropertyChanged();
    }
}
