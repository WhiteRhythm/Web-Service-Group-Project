package org.global.academy;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    
    private static List<Flashcard> flashcards;

    public static void main(String[] args) {
        // 1. Initialize data
        flashcards = createBurmeseFlashcards();
        
        // 2. Configure Spark
        port(8080);
        staticFiles.location("/public");
        
        // 3. Enable CORS
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
        });

        Gson gson = new Gson();
        Random random = new Random();

        // --- ROUTES ---

        get("/flashcard", (req, res) -> {
            int randomIndex = random.nextInt(flashcards.size());
            Flashcard randomCard = flashcards.get(randomIndex);
            res.type("application/json");
            return gson.toJson(randomCard);
        });

        post("/login", (req, res) -> {
            LoginRequest lr = gson.fromJson(req.body(), LoginRequest.class);
            if ("alice".equals(lr.username) && "secret".equals(lr.password)) {
                res.type("application/json");
                return gson.toJson(new LoginResponse("fake-jwt-token", lr.username));
            } else {
                res.status(401);
                res.type("application/json");
                return gson.toJson(new ErrorResponse("Invalid credentials"));
            }
        });
        
        System.out.println("Server is running on http://localhost:8080");
    }

    // --- DATA POPULATION ---
    private static List<Flashcard> createBurmeseFlashcards() {
        List<Flashcard> cards = new ArrayList<>();
        
        // --- Group 1 (Ka Wag) ---
        cards.add(new BurmeseConsonantFlashcard("က", "Ka - (Chicken)", 
            "က (ကကြီး / ka.gji: / က - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် ပထမဆုံး ဗျည်းအက္ခရာဖြစ်သည်။ \nKa (Ka Gyi / ka.gji: / ka -group) is the first of the thirty-three Burmese alphabets."));
            
        cards.add(new BurmeseConsonantFlashcard("ခ", "Kha - (Spiral)", 
            "ခ (ခကွေး / kʰa.gwe: / က - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် ဒုတိယမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nKha (Kha Kway / kʰa.gwe: / ka -group) is the second of the thirty-three Burmese alphabets."));
            
        cards.add(new BurmeseConsonantFlashcard("ဂ", "Ga - (Snail)", 
            "ဂ (ဂငယ် / ga.ngɛ / က - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တတိယမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nGa (Ga Nge / ga.ngɛ / ka -group) is the third of the thirty-three Burmese alphabets."));
            
        cards.add(new BurmeseConsonantFlashcard("ဃ", "Gha - (Great)", 
            "ဃ (ဃကြီး / ga.gji: / က - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် စတုတ္ထမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nGha (Gha Gyi / ga.gji: / ka -group) is the fourth of the thirty-three Burmese alphabets."));
            
        cards.add(new BurmeseConsonantFlashcard("င", "Nga - (Fish)", 
            "င (င / nga. / က - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် ပဉ္စမမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nNga (Nga / nga. / ka -group) is the fifth of the thirty-three Burmese alphabets."));
        
        // --- Group 2 (Sa Wag) ---
        cards.add(new BurmeseConsonantFlashcard("စ", "Sa - (Sparrow)", 
            "စ (စလုံး / sa.lone: / စ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် ဆဌမမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nSa (Sa Lone / sa.lone: / sa -group) is the sixth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဆ", "Hsa - (Twisted)", 
            "ဆ (ဆလိမ် / hsa.lein / စ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် သတ္တမမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nHsa (Hsa Lein / hsa.lein / sa -group) is the seventh of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဇ", "Za - (Egret)", 
            "ဇ (ဇကွဲ / za.gwe: / စ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် အဋ္ဌမမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nZa (Za Gwe / za.gwe: / sa -group) is the eighth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဈ", "Zha - (Big Eye)", 
            "ဈ (ဈမျဉ်းဆွဲ / za.myin:swe: / စ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နဝမမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nZha (Zha Myin Zwe / za.myin:swe: / sa -group) is the ninth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ည", "Nya - (Palm Tree)", 
            "ည (ည / nya. / စ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် ဒသမမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nNya (Nya / nya. / sa -group) is the tenth of the thirty-three Burmese alphabets."));

        // --- Group 3 (Tta Wag - Retroflex) ---
        cards.add(new BurmeseConsonantFlashcard("ဋ", "Tta - (Post)", 
            "ဋ (ဋသန်လျင်းချိတ် / tta. / ဋ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်တစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nTta (Tta Than Lyin Cheit / tta. / tta -group) is the eleventh of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဌ", "Ttha - (Circle)", 
            "ဌ (ဌဝမ်းဘဲ / ttha. / ဋ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်နှစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nTtha (Ttha Wan Be / ttha. / tta -group) is the twelfth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဍ", "Dda - (Duck)", 
            "ဍ (ဍရင်ကောက် / dda. / ဋ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်သုံးလုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nDda (Dda Yin Kauk / dda. / tta -group) is the thirteenth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဎ", "Ddha - (Water Filter)", 
            "ဎ (ဎရေမှုတ် / ddha. / ဋ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်လေးလုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nDdha (Ddha Yay Hmut / ddha. / tta -group) is the fourteenth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဏ", "Nna - (Money)", 
            "ဏ (ဏကြီး / nna. / ဋ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်ငါးလုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nNna (Nna Gyi / nna. / tta -group) is the fifteenth of the thirty-three Burmese alphabets."));

        // --- Group 4 (Ta Wag - Dental) ---
        cards.add(new BurmeseConsonantFlashcard("တ", "Ta - (Soldier)", 
            "တ (တဝမ်းပူ / ta. / တ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်ခြောက်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nTa (Ta Wan Pu / ta. / ta -group) is the sixteenth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ထ", "Tha - (Bag)", 
            "ထ (ထဆင်ထူး / tha. / တ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်ခုနစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nTha (Tha Sin Htoo / tha. / ta -group) is the seventeenth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဒ", "Da - (Peacock)", 
            "ဒ (ဒဒွေး / da. / တ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်ရှစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nDa (Da Dway / da. / ta -group) is the eighteenth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဓ", "Dha - (Flag)", 
            "ဓ (ဓအောက်ခြိုက် / dha. / တ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် တစ်ဆယ့်ကိုးလုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nDha (Dha Auk Chaik / dha. / ta -group) is the nineteenth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("န", "Na - (Ear)", 
            "န (နငယ် / na. / တ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nNa (Na Nge / na. / ta -group) is the twentieth of the thirty-three Burmese alphabets."));

        // --- Group 5 (Pa Wag - Labial) ---
        cards.add(new BurmeseConsonantFlashcard("ပ", "Pa - (Box)", 
            "ပ (ပစောက် / pa. / ပ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်တစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nPa (Pa Sauk / pa. / pa -group) is the twenty-first of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဖ", "Pha - (Pagoda)", 
            "ဖ (ဖဦးထုပ် / pha. / ပ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်နှစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nPha (Pha Oo Htoke / pha. / pa -group) is the twenty-second of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဗ", "Ba - (Drum)", 
            "ဗ (ဗထက်ခြိုက် / ba. / ပ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်သုံးလုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nBa (Ba Htet Chaik / ba. / pa -group) is the twenty-third of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဘ", "Bha - (Hermit)", 
            "ဘ (ဘကုန်း / bha. / ပ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်လေးလုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nBha (Bha Kone / bha. / pa -group) is the twenty-fourth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("မ", "Ma - (Mushroom)", 
            "မ (မ / ma. / ပ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်ငါးလုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nMa (Ma / ma. / pa -group) is the twenty-fifth of the thirty-three Burmese alphabets."));

        // --- Group 6 (Miscellaneous) ---
        cards.add(new BurmeseConsonantFlashcard("ယ", "Ya - (Pet)", 
            "ယ (ယပက်လက် / ya. / အ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်ခြောက်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nYa (Ya Pet Let / ya. / a -group) is the twenty-sixth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ရ", "Ra - (Sun/Carriage)", 
            "ရ (ရကောက် / ra. / အ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်ခုနစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nRa (Ra Kauk / ra. / a -group) is the twenty-seventh of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("လ", "La - (Moon)", 
            "လ (လ / la. / အ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်ရှစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nLa (La / la. / a -group) is the twenty-eighth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဝ", "Wa - (Cotton/Circle)", 
            "ဝ (ဝလုံး / wa. / အ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် နှစ်ဆယ့်ကိုးလုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nWa (Wa Lone / wa. / a -group) is the twenty-ninth of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("သ", "Tha - (Fruit)", 
            "သ (သ / tha. / အ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် သုံးဆယ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nTha (Tha / tha. / a -group) is the thirtieth of the thirty-three Burmese alphabets."));
        
        // --- Group 7 (Miscellaneous) ---
        cards.add(new BurmeseConsonantFlashcard("ဟ", "Ha - (Laugh)", 
            "ဟ (ဟ / ha. / အ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် သုံးဆယ့်တစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nHa (Ha / ha. / a -group) is the thirty-first of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("ဠ", "La - (Great)", 
            "ဠ (ဠကြီး / la. / အ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် သုံးဆယ့်နှစ်လုံးမြောက် ဗျည်းအက္ခရာဖြစ်သည်။ \nLa (La Gyi / la. / a -group) is the thirty-second of the thirty-three Burmese alphabets."));

        cards.add(new BurmeseConsonantFlashcard("အ", "A - (Official/Bowl)", 
            "အ (အ / a. / အ - အစု) သည် မြန်မာဗျည်းအက္ခရာ သုံးဆယ့်သုံးလုံးအနက် သုံးဆယ့်သုံးလုံးမြောက် (နောက်ဆုံး) ဗျည်းအက္ခရာဖြစ်သည်။ \nA (A / a. / a -group) is the thirty-third and last of the thirty-three Burmese alphabets."));

        return cards;
    }

    static class LoginRequest {
        String username;
        String password;
    }

    static class LoginResponse {
        String token;
        String username;
        LoginResponse(String t, String u) {
            token = t;
            username = u;
        }
    }

    static class ErrorResponse {
        String error;
        ErrorResponse(String e) {
            error = e;
        }
    }
}