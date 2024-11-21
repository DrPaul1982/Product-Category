package src.app;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static src.app.Currency.CURRENCY;

public class Main {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Orange", "Fruits", 38.99),
                new Product("Pineapple", "Fruits", 120.99),
                new Product("Banana", "Fruits", 28.69),
                new Product("Tomato", "Vegetables", 51.29),
                new Product("Cucumber", "Vegetables", 60.99),
                new Product("Potato", "Vegetables", 21.3)
        );

        Map<String, List<Product>> resultCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        Map<String, Double> resultCategoryAveragePrice = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));

        String maxEveragePriceCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("\nProducts by category: " + resultCategory);
        System.out.println("\n" +
                "Average price by category in " + CURRENCY + " is " + resultCategoryAveragePrice);
        System.out.println("\n" +
                "Category with the highest average price is " + maxEveragePriceCategory + ".");

}
}
