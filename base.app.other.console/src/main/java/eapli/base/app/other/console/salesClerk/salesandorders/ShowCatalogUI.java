package eapli.base.app.other.console.salesClerk.salesandorders;

import eapli.base.productmanagement.application.OptionSort;
import eapli.base.productmanagement.application.ShowCatalogController;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.EnumSelectWidget;
import eapli.framework.visitor.Visitor;


public class ShowCatalogUI extends AbstractListUI<Product> {

    private final ShowCatalogController theController = new ShowCatalogController();


    @Override
    protected Iterable<Product> elements() {
        EnumSelectWidget<OptionSort> selectOption = new EnumSelectWidget<>("SORTING OPTIONS",OptionSort.class);
        selectOption.show();
        OptionSort option = selectOption.selectedElement();
        return this.theController.getProductsSorted(option);
    }

    @Override
    protected Visitor<Product> elementPrinter() {
        return new ProductPrinter();
    }

    @Override
    protected String elementName() {
        return "PRODUCT";
    }

    @Override
    protected String listHeader() {
        return String.format(" %s %20s %-30s %20s %-15s %20s %-15s %20s %s \n" +
                "==============================================================================================================================================================================="
                ,"PRODUCT CODE","|","SHORT DESCRIPTION","|","BRAND","|","CATEGORY","|","PRICE");
             }

    @Override
    protected String emptyMessage() {
        return "NO DATA";
    }


    @Override
    public String headline() {
        return "PRODUCT CATALOG";
    }
}
