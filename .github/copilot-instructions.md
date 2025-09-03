<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->

# Job App Tailor - AI Resume & JD Matcher

## Project Overview
This is a full-stack web application that helps job seekers tailor their resumes to specific job postings using AI-powered analysis and feedback.

## Tech Stack
- **Backend**: Spring Boot (Java 17), PostgreSQL, JPA/Hibernate, OpenAI API
- **Frontend**: React, Vite, Tailwind CSS, Axios
- **Infrastructure**: Docker Compose, JWT Authentication

## Project Completion Status
- [x] Project structure created with backend and frontend
- [x] Database models and repositories implemented
- [x] REST API controllers and services created
- [x] React frontend with component structure
- [x] Docker configuration for deployment
- [x] Build and development tasks configured
- [x] Comprehensive documentation

## Key Features Implemented
- Resume parsing (PDF/text upload)
- Job description fetching (URL or manual input)
- AI-powered matching and scoring
- Resume bullet tailoring
- Cover letter generation
- Interview preparation content
- Gap analysis and recommendations

## Getting Started
1. Copy `.env.example` to `.env` and configure API keys
2. Use VS Code tasks to start the application or run manually:
   - Backend: `cd backend && mvn spring-boot:run`
   - Frontend: `cd frontend && npm run dev`
3. Access the application at http://localhost:3000
