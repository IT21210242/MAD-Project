package com.example.travelsl.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.travelsl.R

class AboutAyurvedicMedicine : AppCompatActivity() {

    private lateinit var aboutAyurveda : TextView
    private lateinit var aboutHistory: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_ayurevedic_medicine)

        aboutAyurveda = findViewById(R.id.tvAboutAyurveda)
        aboutHistory = findViewById(R.id.tvAboutHistory)
        setAbout()


    }

    private fun setAbout() {
        aboutAyurveda.text = "In Sri Lanka ayurveda medicine is based on the country’s centuries old treasure trove of indigenous knowledge base, natural environment and cultural repertory. " +
                ". According to archeological evidence, human civilisation dates back 30,000 years. Cave men of that age domesticated many wild plant varieties and used them for food and medicines." +
                "\nSri Lanka’s indigenous medicine has similar origins, linked to the 30,000 year old native habitants of the land known as the Balangoda man.\n" +
                "With its ancient history, traditional ayurveda medicine as it is practiced today in the country, is based on an indigenous heritage preserved over 3000 years.\n" +
                "The royal patronage extended throughout history to the nation’s healthcare was supported by the ayurveda practice of the time.\n" +
                "Sri Lankan tradition of indigenous medicine has its own teachings based on outstanding norms and laws. Historical information gathered from chronicles, inscriptions and epigraphic records " +
                "indicate that the country’s royalty extended their patronage and custodianship to healthcare through traditional medicine. Throughout its course, history shows that traditional medicine has been " +
                "preserved and practiced to build and maintain the health of the nation. While there is historical evidence of the existence of a national healthcare system, ancient rock inscriptions reveal the " +
                "existence of a contemporary medical service in the country. These ancient hospital sites, active centuries ago have now turned into tourist attractions, symbolizing the healing and care prevalent at that time. " +
                "With the royal patronage bestowed on them ayurveda physicians of ancient times enjoyed a noble position in the social hierarchy, endorsing the local belief that, “If you cannot be a king, become a healer”." +
                "\nThis inter-relationship between ayurveda and royalty has placed ayurveda and its connections with Buddhism in the forefront of Sri Lankan life."

        aboutHistory.text = "Ayurveda in Sanskrit means ‘the Science of Life. " +
                "This holistic system of medicine prevalent in Asia, is believed to have evolved from divine sources and practiced by sages in India over 3000-5000 years ago. " +
                "Ayurveda views humans as a complex whole, including their external/ internal environment and derives its practice from universal laws of nature." +
                "\nAyurveda theory describes the human being as a triune of body, mind and spirit which are interrelated and interdependent. " +
                "The whole universe is represented in this theory as macrocosm and man as microcosm or a minute model of universe."


    }
}