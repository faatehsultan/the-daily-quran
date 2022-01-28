package com.codefumes.thedailyquran.data

// always import and use these functions, do not import data directly
fun getAllSupplications(): List<Supplication> {
    return SupplicationDatabase
}
fun getSupplicationsCount():Int {
    return SupplicationDatabase.size
}
fun getAllSingleSupplications(mainSupplicationID: Int): Supplication {
    SupplicationDatabase.forEach {
        item ->
            if (item.id == mainSupplicationID) {
                return item
            }
    }
    return Supplication(-1, "", listOf<SingleSupplication>())
}

data class SingleSupplication(val id_: Int, val arabic_: String, val urdu_: String, val description_: String, val reference_: String) {
    val id: Int = id_
    val arabic: String = arabic_
    val urdu: String = urdu_
    val description: String = description_
    val reference: String = reference_
}

data class Supplication(val id_: Int, val title_: String, val singleSupplications_: List<SingleSupplication>) {
    val id: Int = id_
    val title: String = title_
    val singleSupplications = singleSupplications_
}

// constant data, NOT TO BE IMPORTED DIRECTLY
val SupplicationDatabase = listOf<Supplication>(
    Supplication(1, "حمد و ثنا اور توبہ و استغفار", listOf(
        SingleSupplication(
            1,
            "سُبْحَانَ اللّٰہِ وَبِحَمْدِہٖ",
            "پاک ہے اللہ اپنی خوبیوں سمیت",
            "رسول اللہ صلی اللہ علیہ وآلہ وسلم نے فرمایا: ’’جو شخص ایک دن میں سو مرتبہ کہے: اس کے گناہ سمندر کی جھاگ کے برابر بھی ہوں تو معاف ہوجاتے ہیں۔",
            "صحیح البخاری، الدعوات، باب فضل التسبیح، حدیث: 6405، وصحیح مسلم، الذکر والدعائ، باب فضل التھلیل والتسبیح، حدیث: 6842"),
    )),
    Supplication(2, "سوتے وقت کی دعائیں", listOf(
        SingleSupplication(
            1,
            " ",
            " ",
            "دونوں ہتھیلیاں ساتھ ملا کر سورۂ اخلاص، سورۂ فلق اور سورۂ ناس پڑھے، پھر ان میں پھونک مارے اور دونوں کو اپنے جسم پر جہاں تک ممکن ہو پھیرے، سر، چہرے اور جسم کے سامنے والے حصے سے شروع کرے۔ اس طرح تین دفعہ کرے۔\n",
            "صحیح البخاری، فضائل القرآن، باب فضل المعوذات، حدیث: 5017"),
        SingleSupplication(
            2,
            "اَللّٰہُ لَاۤ اِلٰہَ اِلَّا ہُوَۚ اَلۡحَیُّ الۡقَیُّوۡمُ ۬ ۚ لَا تَاۡخُذُہٗ سِنَۃٌ وَّ لَا نَوۡمٌ ؕ لَہٗ مَا فِی السَّمٰوٰتِ وَ مَا فِی الۡاَرۡضِ ؕ مَنۡ ذَا الَّذِیۡ یَشۡفَعُ  عِنۡدَہٗۤ  اِلَّا بِاِذۡنِہٖ ؕ یَعۡلَمُ مَا بَیۡنَ اَیۡدِیۡہِمۡ وَ مَا خَلۡفَہُمۡ  ۚ وَ لَا یُحِیۡطُوۡنَ بِشَیۡءٍ مِّنۡ عِلۡمِہٖۤ اِلَّا بِمَا شَآءَ ۚ وَسِعَ کُرۡسِیُّہُ السَّمٰوٰتِ وَ الۡاَرۡضَ ۚ وَ لَا یَـُٔوۡدُہٗ حِفۡظُہُمَا ۚ وَ ہُوَ الۡعَلِیُّ  الۡعَظِیۡمُ ﴿۲۵۵﴾ ",
            "اللہ (وہ ہے کہ) اس کے سوا کوئی معبود نہیں، وہ زندۂ جاوید (اور) قائم و دائم ہے۔ اسے اونگھ آتی ہے نہ نیند، اسی کا ہے جو کچھ آسمانوں میں ہے اور جو کچھ زمین میں ہے۔ کون ہے وہ جو اس کے ہاں سفارش کرسکے مگر اس کی اجازت سے؟ وہ جانتا ہے جو کچھ لوگوں کے سامنے ہے اور جو کچھ ان کے پیچھے ہے۔ اور وہ اس کے علم میں سے کسی چیز کا احاطہ نہیں کرسکتے مگر جس قدر وہ خود چاہے۔ اس کی کرسی نے آسمانوں اور زمین کو گھیر رکھا ہے اور اسے ان دونوں کی حفاظت نہیں تھکاتی اور وہ بلندتر، نہایت عظمت والا ہے",
            "جب تم بستر پر پہنچو اور آیۃ الکرسی: (اَللّٰہُ لَآ اِلٰہَ اِلَّا ھُوَ اَلْحَیُّ الْقَیُّوْمُ) مکمل پڑھو تو اللہ کی طرف سے ایک محافظ مقرر ہوجائے گا اور شیطان صبح تک تمہارے قریب بھی نہ آسکے گا۔",
            "صحیح البخاری، فضائل القرآن، باب فضل سورۃ البقرۃ، حدیث: 5010"),
        SingleSupplication(
            3,
            "اٰمَنَ الرَّسُوۡلُ بِمَاۤ  اُنۡزِلَ اِلَیۡہِ مِنۡ رَّبِّہٖ وَ الۡمُؤۡمِنُوۡنَ ؕ کُلٌّ اٰمَنَ بِاللّٰہِ وَ مَلٰٓئِکَتِہٖ وَ کُتُبِہٖ وَ رُسُلِہٖ ۟ لَا نُفَرِّقُ بَیۡنَ  اَحَدٍ مِّنۡ رُّسُلِہٖ ۟ وَ قَالُوۡا سَمِعۡنَا وَ اَطَعۡنَا ٭۫ غُفۡرَانَکَ رَبَّنَا وَ اِلَیۡکَ الۡمَصِیۡرُ ﴿۲۸۵﴾ لَا یُکَلِّفُ اللّٰہُ نَفۡسًا اِلَّا وُسۡعَہَا ؕ لَہَا مَا کَسَبَتۡ وَ عَلَیۡہَا مَا اکۡتَسَبَتۡ ؕ رَبَّنَا لَا تُؤَاخِذۡنَاۤ  اِنۡ نَّسِیۡنَاۤ  اَوۡ اَخۡطَاۡنَا ۚ رَبَّنَا وَ لَا  تَحۡمِلۡ  عَلَیۡنَاۤ  اِصۡرًا کَمَا حَمَلۡتَہٗ عَلَی الَّذِیۡنَ مِنۡ قَبۡلِنَا ۚ رَبَّنَا وَ لَا  تُحَمِّلۡنَا مَا لَا طَاقَۃَ لَنَا بِہٖ ۚ وَ اعۡفُ عَنَّا ٝ وَ اغۡفِرۡ لَنَا ٝ وَ ارۡحَمۡنَا ٝ اَنۡتَ مَوۡلٰىنَا فَانۡصُرۡنَا عَلَی الۡقَوۡمِ الۡکٰفِرِیۡنَ ﴿۲۸۶﴾",
            "رسول (صلی اللہ علیہ وآلہ وسلم) اس (ہدایت) پر ایمان لائے ہیں جو ان کے رب کی طرف سے ان پر نازل کی گئی ہے اور سارے مومن بھی، سب اللہ پر اور اس کے فرشتوں پر اور اس کی کتابوں پر اور اس کے رسولوں پر ایمان لائے ہیں۔ (وہ کہتے ہیں:) ہم اس کے رسولوں میں سے کسی ایک میں بھی فرق نہیں کرتے اور وہ کہتے ہیں: ہم نے (حکم) سنا اور اطاعت کی، اے ہمارے رب! ہم تیری بخشش چاہتے ہیں اور ہمیں تیری ہی طرف لوٹ کر آنا ہے۔ اللہ کسی کو اس کی برداشت سے بڑھ کر تکلیف نہیں دیتا، کسی شخص نے جو نیکی کمائی اس کا پھل اسی کے لیے ہے اور جو اس نے برائی کی اس کا وبال بھی اسی پر ہے۔ اے ہمارے رب! اگر ہم سے بھول چوک ہوجائے تو ہماری گرفت نہ کر، اے ہمارے رب! ہم پر ایسا بوجھ نہ ڈال جو تونے ہم سے پہلے لوگوں پر ڈالا تھا۔ اے ہمارے رب! جس بوجھ کو اٹھانے کی ہم میں طاقت نہیں، وہ ہم سے نہ اٹھوا اور ہم سے درگزر فرما اور ہمیں بخش دے اور ہم پر رحم فرما، تو ہی ہمارا کارساز ہے، پس تو کافروں کے مقابلے میں ہماری مدد فرما",
            "جو شخص درج ذیل دو آیات رات کے وقت پڑھتا ہے تو یہ اس کے لیے کافی ہوجاتی ہیں:",
            "البقرۃ 286,285:2، وصحیح البخاری، فضائل القرآن، باب فصل سورۃ البقرۃ، حدیث: 5009، وصحیح مسلم، صلاۃ المسافرین، باب فضل الفاتحۃ وخواتیم سورۃ البقرۃ، حدیث: 1878"
        ),
        SingleSupplication(
            4,
            " ",
            " ",
            "سورت الم تنزیل السجدۃ اور سورت تبارک الذی بیدہ الملک پڑھے۔",
            "جامع الترمذی، الدعوات، باب منہ (فی قراء ۃ سور: الکافرون والسجدۃ…)، حدیث: 3404"),
    )),
    Supplication(3, "رات کو کروٹ بدلتے وقت کی دعا", listOf(
        SingleSupplication(
            1,
            "لَآ اِلٰہَ اِلَّا اللّٰہُ الْوَاحِدُ الْقَھَّارُ رَبُّ السَّمٰوٰتِ وَالْاَرْضِ وَمَا بَیْنَھُمَا الْعَزِیْزُ الْغَفَّارُ\n",
            " ",
            "اللہ کے سوا کوئی معبود نہیں، وہ یکتا ہے، زبردست ہے، رب ہے آسمانوں اور زمین کا اور (ان کا) جو کچھ ان دونوں کے درمیان ہے۔ بہت غالب، بہت بخشنے والا ہے",
            "المستدرک للحاکم: 540/1، حدیث: 1980"),
    )),
)
