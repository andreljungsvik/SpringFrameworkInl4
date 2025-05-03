package se.yrgo.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.domain.Action;
import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;
import se.yrgo.services.calls.CallHandlingService;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;
import se.yrgo.services.diary.DiaryManagementService;

public class SimpleClient {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

        CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
        DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);

        CallHandlingService callService = container.getBean(CallHandlingService.class);

        Call newCall = new Call("Dom called from Twin Peaks Company");
        Action action1 = new Action("Call back Dom as soon as possible for feedback",
                new GregorianCalendar(2019, 12, 10), "user");
        Action action2 = new Action("Check if Dom called again",
                new GregorianCalendar(2019, 12, 11), "user");
        List<Action> actions = new ArrayList<Action>();
        actions.add(action1);
        actions.add(action2);

        try {
            for (Action action : actions) {
                callService.recordCall("NV10", newCall, action);
            }
        } catch (CustomerNotFoundException e) {
            System.err.println("This customer does not exist.");
        }

        System.out.println("Here are the actions:");
        Collection<Action> incompleteActions = diaryService.getAllIncompleteActions("user");
        for (Action action : incompleteActions) {
            System.out.println(action);
        }

        container.close();
    }
}