# Job App Tailor - AI Resume & JD Matcher

A web application that helps job seekers tailor their resumes to specific job postings using AI-powered analysis and feedback.

## Features

- **Resume Analysis**: Upload or paste resumes in PDF, Markdown, or plain text format
- **Job Description Fetching**: Automatically fetch job descriptions from Greenhouse/Lever APIs or manual input
- **AI-Powered Matching**: Uses OpenAI to analyze gaps and provide targeted feedback
- **Resume Tailoring**: Generate tailored resume bullets and cover letter paragraphs
- **Interview Preparation**: Get relevant interview questions and STAR story prompts
- **ATS Optimization**: Keyword alignment scoring for ATS systems

## Tech Stack

### Backend
- **Spring Boot** (Java 17)
- **PostgreSQL** database
- **JPA/Hibernate** for ORM
- **OpenAI API** integration
- **Apache PDFBox/Tika** for PDF parsing

### Frontend
- **React** with Vite
- **Tailwind CSS** for styling
- **Axios** for API calls

### Infrastructure
- **Docker Compose** for local development
- **JWT Authentication**
- **RESTful API** design

## Project Structure

```
├── backend/                 # Spring Boot application
│   ├── src/main/java/      # Java source code
│   ├── src/main/resources/ # Configuration files
│   └── pom.xml             # Maven dependencies
├── frontend/               # React application
│   ├── src/                # React source code
│   ├── public/             # Static assets
│   └── package.json        # npm dependencies
├── docker-compose.yml      # Docker configuration
└── README.md              # This file
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 18+ and npm
- Docker & Docker Compose (optional)
- PostgreSQL (or use Docker)
- OpenAI API key

### Environment Setup

1. Copy the environment template:
   ```bash
   cp .env.example .env
   ```

2. Edit `.env` file and add your API keys:
   ```
   OPENAI_API_KEY=your-openai-api-key-here
   JWT_SECRET=your-secure-jwt-secret-key
   ```

### Installation

#### Option 1: Using Docker Compose (Recommended)
```bash
# Start all services (database, backend, frontend)
docker-compose up -d

# View logs
docker-compose logs -f
```

#### Option 2: Manual Setup

1. **Start PostgreSQL Database**
   ```bash
   # Using Docker
   docker run --name jobapp-postgres -e POSTGRES_DB=jobapptailor -e POSTGRES_USER=jobapp_user -e POSTGRES_PASSWORD=jobapp_password -p 5432:5432 -d postgres:15
   ```

2. **Start Backend**
   ```bash
   cd backend
   mvn spring-boot:run
   ```

3. **Start Frontend**
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

### Development

#### Using VS Code Tasks
This project includes VS Code tasks for easy development:

- **Start Full Application**: `Ctrl+Shift+P` → "Tasks: Run Task" → "Start Full Application"
- **Start Backend Only**: "Start Backend Server"
- **Start Frontend Only**: "Start Frontend Server"
- **Build Backend**: "Build Backend"
- **Build Frontend**: "Build Frontend"

#### Manual Commands

##### Backend Development
```bash
cd backend
mvn compile                    # Compile
mvn test                      # Run tests
mvn spring-boot:run           # Start development server
mvn clean package            # Build JAR file
```

##### Frontend Development
```bash
cd frontend
npm install                   # Install dependencies
npm run dev                   # Start development server
npm run build                 # Build for production
npm run preview               # Preview production build
```

## API Endpoints

- `POST /api/resume/parse` - Parse and extract resume content
- `POST /api/job/fetch` - Fetch job description from URL
- `POST /api/match` - Generate matching score and analysis
- `POST /api/ai/tailor` - Generate AI-powered tailored content

## Database Schema

- **users** - User accounts and authentication
- **resumes** - Resume storage and extracted data
- **job_posts** - Job posting data and requirements
- **matches** - Resume-job matching results
- **ai_outputs** - AI-generated content and feedback

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

MIT License
