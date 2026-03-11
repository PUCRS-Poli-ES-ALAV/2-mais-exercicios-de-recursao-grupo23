import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RecursiveExercises {
    
    /*
     * 1. Modelagem: fatorial(n) = 1, se n = 0
     *               fatorial(n) = n * fatorial(n-1), se n > 0
     */
    public static int factorial(int n) {
        // Caso base
        if (n == 0) {
            return 1;
        }
        // Passo recursivo
        return n * factorial(n - 1);
    }
    
    /*
     * 2. Modelagem: somatorio(n) = 0, se n = 0
     *               somatorio(n) = n + somatorio(n-1), se n > 0
     */
    public static int sumToZero(int n) {
        // Caso base
        if (n == 0) {
            return 0;
        }
        // Passo recursivo
        return n + sumToZero(n - 1);
    }
    
    /*
     * 3. Modelagem: fibonacci(n) = n, se n = 0 ou n = 1
     *               fibonacci(n) = fibonacci(n-1) + fibonacci(n-2), se n > 1
     */
    public static int fibonacci(int n) {
        // Casos base
        if (n == 0 || n == 1) {
            return n;
        }
        // Passo recursivo
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    /*
     * 4. Modelagem: somatorioEntre(k, j) = k, se k = j
     *               somatorioEntre(k, j) = k + somatorioEntre(k+1, j), se k < j
     *               somatorioEntre(k, j) = k + somatorioEntre(k-1, j), se k > j
     */
    public static int sumBetween(int k, int j) {
        // Caso base
        if (k == j) {
            return k;
        }
        // Passo recursivo para k < j
        if (k < j) {
            return k + sumBetween(k + 1, j);
        }
        // Passo recursivo para k > j
        return k + sumBetween(k - 1, j);
    }
    
    /*
     * 5. Modelagem: isPal(s) = true, se s.length() <= 1
     *               isPal(s) = (primeiro caractere == último caractere) 
     *                          && isPal(substring do meio), se s.length() > 1
     */
    public static boolean isPal(String s) {
        // Caso base: strings vazias ou de um caractere são palíndromes
        if (s.length() <= 1) {
            return true;
        }
        // Verifica primeiro e último caracteres
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        // Passo recursivo: verifica a substring sem o primeiro e último caracteres
        return isPal(s.substring(1, s.length() - 1));
    }
    
    /*
     * 6. Modelagem: convBase2(n) = "0", se n = 0
     *               convBase2(n) = convBase2(n/2) + (n%2), se n > 0
     */
    public static String convBase2(int n) {
        // Caso base
        if (n == 0) {
            return "0";
        }
        // Caso especial para n = 1
        if (n == 1) {
            return "1";
        }
        // Passo recursivo
        return convBase2(n / 2) + (n % 2);
    }
    
    /*
     * 7. Modelagem: sumArrayList(list) = 0, se lista vazia
     *               sumArrayList(list) = primeiro elemento + 
     *                                    sumArrayList(resto da lista)
     */
    public static int sumArrayList(ArrayList<Integer> list) {
        return sumArrayListHelper(list, 0);
    }
    
    private static int sumArrayListHelper(ArrayList<Integer> list, int index) {
        // Caso base: fim da lista
        if (index >= list.size()) {
            return 0;
        }
        // Passo recursivo
        return list.get(index) + sumArrayListHelper(list, index + 1);
    }
    
    /*
     * 8. Modelagem: findBiggest(list) = elemento único, se lista tem 1 elemento
     *               findBiggest(list) = max(primeiro, findBiggest(resto da lista))
     */
    public static int findBiggest(ArrayList<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Lista não pode ser vazia");
        }
        return findBiggestHelper(list, 0, list.get(0));
    }
    
    private static int findBiggestHelper(ArrayList<Integer> list, int index, int currentMax) {
        // Caso base: fim da lista
        if (index >= list.size()) {
            return currentMax;
        }
        // Atualiza o máximo se necessário
        if (list.get(index) > currentMax) {
            currentMax = list.get(index);
        }
        // Passo recursivo
        return findBiggestHelper(list, index + 1, currentMax);
    }
    
    /*
     * 9. Modelagem: findSubStr(str, match) = true, se match vazio
     *               findSubStr(str, match) = true, se str começa com match
     *               findSubStr(str, match) = findSubStr(substring de str, match)
     */
    public static boolean findSubStr(String str, String match) {
        // Casos base
        if (match.isEmpty()) {
            return true;
        }
        if (str.isEmpty()) {
            return false;
        }
        // Verifica se a string atual começa com match
        if (str.startsWith(match)) {
            return true;
        }
        // Passo recursivo: verifica a partir do próximo caractere
        return findSubStr(str.substring(1), match);
    }
    
    /*
     * 10. Modelagem: nroDigit(n) = 1, se |n| < 10
     *                nroDigit(n) = 1 + nroDigit(n/10), se |n| >= 10
     */
    public static int nroDigit(int n) {
        // Caso base: números de um dígito
        if (Math.abs(n) < 10) {
            return 1;
        }
        // Passo recursivo
        return 1 + nroDigit(n / 10);
    }
    
    /*
     * 11. Modelagem: permutations(s) = lista contendo s, se s tem 1 caractere
     *                permutations(s) = para cada caractere c em s:
     *                    para cada permutação p de s sem c:
     *                        adicionar c + p à lista de permutações
     */
    public static ArrayList<String> permutations(String s) {
        ArrayList<String> result = new ArrayList<>();
        
        // Caso base
        if (s.length() <= 1) {
            result.add(s);
            return result;
        }
        
        // Passo recursivo
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            String remaining = s.substring(0, i) + s.substring(i + 1);
            
            // Obtém todas as permutações da string restante
            ArrayList<String> subPermutations = permutations(remaining);
            
            // Adiciona o caractere atual ao início de cada permutação
            for (String perm : subPermutations) {
                result.add(currentChar + perm);
            }
        }
        
        return result;
    }
    
    // Método main para testar todos os métodos
    public static void main(String[] args) {
        System.out.println("=== Testando os métodos recursivos ===");
        
        // Teste 1: Fatorial
        System.out.println("\n1. Fatorial de 5: " + factorial(5));
        
        // Teste 2: Somatório até zero
        System.out.println("2. Somatório de 5 até 0: " + sumToZero(5));
        
        // Teste 3: Fibonacci
        System.out.println("3. Fibonacci de 7: " + fibonacci(7));
        
        // Teste 4: Somatório entre números
        System.out.println("4. Somatório entre 3 e 7: " + sumBetween(3, 7));
        System.out.println("   Somatório entre 10 e 5: " + sumBetween(10, 5));
        
        // Teste 5: Palíndrome
        System.out.println("5. 'arara' é palíndrome? " + isPal("arara"));
        System.out.println("   'casa' é palíndrome? " + isPal("casa"));
        
        // Teste 6: Conversão para binário
        System.out.println("6. 42 em binário: " + convBase2(42));
        
        // Teste 7: Somatório de ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(1);
        System.out.println("7. Somatório da lista [5,3,8,1]: " + sumArrayList(list));
        
        // Teste 8: Maior elemento
        System.out.println("8. Maior elemento da lista: " + findBiggest(list));
        
        // Teste 9: Substring
        System.out.println("9. 'abc' contém 'bc'? " + findSubStr("abc", "bc"));
        System.out.println("   'abc' contém 'bd'? " + findSubStr("abc", "bd"));
        
        // Teste 10: Número de dígitos
        System.out.println("10. Número de dígitos de 12345: " + nroDigit(12345));
        System.out.println("    Número de dígitos de -987: " + nroDigit(-987));
        
        // Teste 11: Permutações
        System.out.println("11. Permutações de 'abc':");
        ArrayList<String> perms = permutations("abc");
        for (String p : perms) {
            System.out.println("    " + p);
        }
    }
}