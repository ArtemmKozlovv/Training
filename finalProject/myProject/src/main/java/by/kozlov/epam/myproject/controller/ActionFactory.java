package by.kozlov.epam.myproject.controller;

import by.kozlov.epam.myproject.controller.command.admin.AdminAction;
import by.kozlov.epam.myproject.controller.command.admin.request.*;
import by.kozlov.epam.myproject.controller.command.admin.tour.*;
import by.kozlov.epam.myproject.controller.command.admin.user.*;
import by.kozlov.epam.myproject.controller.command.user.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/register", RegisterAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/tourList", UserTourListAction.class);
        actions.put("/admin", AdminAction.class);

        actions.put("/request/list", RequestListAction.class);
        actions.put("/request/add", RequestAddToListAction.class);
        actions.put("/request/delete", RequestDeleteAction.class);

        actions.put("/tour/list", TourListAction.class);
        actions.put("/tour/edit", TourEditAction.class);
        actions.put("/tour/save", TourSaveAction.class);
        actions.put("/tour/delete", TourDeleteAction.class);

        actions.put("/user/removeSelect", RemoveSelectTourForUser.class);
        actions.put("/user/select", SelectTourForUser.class);
        actions.put("/user/selectTour", SelectTourForUserView.class);

        actions.put("/user/list", UserListAction.class);
        actions.put("/user/profile", UserProfileAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/saveProfile", UserSaveProfileAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        if(action != null) {
            try {
                return (Action)action.getConstructor().newInstance();
            } catch(InstantiationException | IllegalAccessException | NullPointerException | NoSuchMethodException | InvocationTargetException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
