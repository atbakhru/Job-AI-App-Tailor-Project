# Job App Tailor 🎯

A **full-stack web application** that empowers job seekers to optimize their resumes for specific job postings using **AI-powered analysis**.  
Built with a **React frontend** and **Spring Boot backend**, featuring **OpenAI GPT-4 integration** for intelligent resume tailoring and career guidance.

---

## 🚀 Key Features

- 📄 **Smart Resume Parser** – Upload PDF/text resumes with automatic skill extraction  
- 🤖 **AI-Powered Matching** – Intelligent compatibility scoring with gap analysis  
- ✍️ **Resume Tailoring** – Generate targeted bullet points and cover letters  
- 🎭 **Interview Prep** – Personalized questions and STAR story prompts  
- 🔍 **ATS Optimization** – Keyword alignment for applicant tracking systems  
- 📊 **Real-time Analysis** – Instant feedback on resume-job compatibility  

---

## 🛠 Tech Stack

**Frontend:** React 18, Vite, Tailwind CSS, Axios  
**Backend:** Java 17, Spring Boot 3.2, Maven, Spring Security  
**Database:** PostgreSQL/H2, JPA/Hibernate  
**AI Integration:** OpenAI GPT-4 API  
**Document Processing:** Apache PDFBox, Apache Tika  
**Infrastructure:** Docker Compose, RESTful APIs

---

## ✨ Features

- **Upload & Parse** resumes in **PDF, Markdown, or plain text** formats  
- **Intelligent skill extraction** from both resumes and job descriptions  
- **AI-powered compatibility scoring** with detailed gap analysis  
- **Generate tailored content** including resume bullets and cover letters  
- **Interview preparation** with role-specific questions and STAR prompts  
- **ATS-friendly optimization** with keyword density analysis  
- **Clean, professional UI** with real-time processing indicators  

---

## 🎯 Who's This For?

Perfect for **job seekers** who want to:  
- **Optimize their resumes** for specific roles  
- Get **AI-powered career insights**  
- **Improve interview preparation**  
- **Beat ATS systems** with targeted optimization  

---

## 🚀 Getting Started

### Prerequisites
- Node.js 18+
- Java 17+
- Maven 3.6+
- OpenAI API Key
- Docker & Docker Compose (optional)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/job-app-tailor.git
   cd job-app-tailor
   ```

2. **Environment Setup**
   ```bash
   # Copy environment template
   cp .env.example .env
   
   # Edit .env file with your API keys
   OPENAI_API_KEY="your-openai-api-key-here"
   JWT_SECRET="your-secure-jwt-secret"
   ```

3. **Quick Start with Docker**
   ```bash
   # Start all services (recommended)
   docker-compose up -d
   
   # View logs
   docker-compose logs -f
   ```

4. **Manual Setup (Alternative)**
   
   **Backend:**
   ```bash
   cd backend
   mvn spring-boot:run
   ```
   
   **Frontend:**
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

5. **Access the application**
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console

## 📝 Usage

1. **Upload Resume**: Drag & drop your PDF resume or paste text content
2. **Add Job Description**: Copy and paste the target job posting
3. **AI Analysis**: Get instant compatibility scores and gap analysis
4. **Optimize Content**: Generate tailored resume bullets and cover letters
5. **Interview Prep**: Access personalized interview questions and STAR prompts

## 🔧 Configuration

### Environment Variables
- `OPENAI_API_KEY`: Your OpenAI API key for GPT-4
- `JWT_SECRET`: Secret key for JWT token generation
- `SPRING_PROFILES_ACTIVE`: Set to 'prod' for production database

### API Endpoints
- `POST /api/resume/parse` - Parse and extract resume content
- `POST /api/job/fetch` - Process job description text
- `POST /api/match` - Generate compatibility analysis
- `POST /api/ai/tailor` - Create tailored content with AI

## 📁 Project Structure

```
├── backend/                 # Spring Boot REST API
│   ├── src/main/java/      # Java source code
│   │   ├── controller/     # REST controllers
│   │   ├── service/        # Business logic
│   │   ├── model/          # JPA entities
│   │   ├── repository/     # Data access layer
│   │   └── dto/            # Data transfer objects
│   ├── src/main/resources/ # Configuration files
│   └── pom.xml             # Maven dependencies
├── frontend/               # React SPA
│   ├── src/                # React components
│   ├── public/             # Static assets
│   └── package.json        # npm dependencies
├── docker-compose.yml      # Multi-service orchestration
└── README.md              # This file
```

## 🎮 Development

### VS Code Tasks (Recommended)
- `Ctrl+Shift+P` → "Tasks: Run Task" → "Start Full Application"
- "Start Backend Server" - Launch Spring Boot API
- "Start Frontend Server" - Launch React dev server
- "Build Backend" - Maven compile and package
- "Build Frontend" - Vite production build

### Manual Commands

**Backend Development:**
```bash
cd backend
mvn compile              # Compile Java source
mvn test                # Run unit tests
mvn spring-boot:run     # Start development server
mvn clean package       # Build production JAR
```

**Frontend Development:**
```bash
cd frontend
npm install             # Install dependencies
npm run dev            # Start development server
npm run build          # Build for production
npm run preview        # Preview production build
```

## 💾 Database Schema

- **users** - User authentication and profiles
- **resumes** - Resume storage with extracted skills
- **job_posts** - Job descriptions and requirements
- **matches** - Compatibility scores and analysis
- **ai_outputs** - AI-generated tailored content

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
   
