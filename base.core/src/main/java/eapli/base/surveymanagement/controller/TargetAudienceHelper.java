package eapli.base.surveymanagement.controller;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.*;
import eapli.base.surveymanagement.repositories.QuestionnaireRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TargetAudienceHelper {


    private final CustomerRepository customerRepository = PersistenceContext.repositories().Customer();

    private final QuestionnaireRepository questionnaireRepository = PersistenceContext.repositories().Questionnaire();

    private Questionnaire questionnaire;

    public TargetAudienceHelper(Questionnaire questionnaire){
        this.questionnaire = questionnaire;
    }

    public void referCostumers(){
        List<Customer> customerList = new ArrayList<>();
        for (TargetRule targetRule : questionnaire.rules()) {
            joinList(customerList, rule(targetRule));
        }

        questionnaire.attributeAudienceSize(AudienceSize.valueOf(customerList.size()));
        questionnaireRepository.save(questionnaire);

        for (Customer customer : customerList){
            customer.addQuestionnaires(questionnaire);
            customerRepository.save(customer);
        }
    }


    private List<Customer> rule(TargetRule target){
        List<List<Customer>> customersListList = new ArrayList<>();
        for (TargetCondition targetCondition : target.conditions()) {
            customersListList.add(handleCondition(targetCondition));
        }

        return TargetCustomers(customersListList);
    }


    private List<Customer> handleCondition(TargetCondition targetCondition){
        if (targetCondition.conditionType() == TargetConditionType.AgeGroup) {
            return customerRepository.findByAge(targetCondition.MinAge(), targetCondition.MaxAge());
        } else if (targetCondition.conditionType() == TargetConditionType.Gender){
            return customerRepository.findByGender(targetCondition.condition());
        } else if (targetCondition.conditionType() == TargetConditionType.Brand){
            return customerRepository.findByBrand(targetCondition.condition());
        } else if (targetCondition.conditionType() == TargetConditionType.Product){
            return customerRepository.findByProduct(targetCondition.condition());
        } else if (targetCondition.conditionType() == TargetConditionType.Category){
            return customerRepository.findByCategory(targetCondition.condition());
        }
        return null;
    }

    //Adds new elements from list2 to list1
    private void joinList(List<Customer> customersList1, List<Customer> customersList2){
        for (Customer customer: customersList2)
            if (!customersList1.contains(customer))
                customersList1.add(customer);
    }

    //creates a new list with all the customers that are present in every list
    private List<Customer> TargetCustomers(List<List<Customer>> customersListList){
        int listSize = customersListList.size();
        List<Customer> finalList = new ArrayList<>();
        HashMap<Customer, Integer> customerMap = new HashMap<>();
        for (List<Customer> customerList : customersListList)
            for (Customer customer : customerList)
                if (customerMap.containsKey(customer)) {
                    Integer integer = customerMap.get(customer);
                    integer += 1;
                    customerMap.put(customer, integer);
                } else {
                    Integer integer = 1;
                    customerMap.put(customer, integer);
                }

        for (Customer customer : customerMap.keySet())
            if (customerMap.get(customer) == listSize)
                finalList.add(customer);


        return finalList;
    }
}
