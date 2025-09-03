import React, { useState } from 'react'
import ResumeUpload from './components/ResumeUpload'
import JobPostInput from './components/JobPostInput'
import MatchResults from './components/MatchResults'

function App() {
  const [resumeData, setResumeData] = useState(null)
  const [jobData, setJobData] = useState(null)
  const [matchResults, setMatchResults] = useState(null)

  return (
    <div className="min-h-screen bg-gray-50">
      <header className="bg-white shadow-sm border-b">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between h-16">
            <h1 className="text-2xl font-bold text-gray-900">
              Job App Tailor
            </h1>
            <p className="text-sm text-gray-600">
              AI-Powered Resume & Job Description Matcher
            </p>
          </div>
        </div>
      </header>

      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          {/* Left Panel - Resume */}
          <div className="space-y-6">
            <ResumeUpload 
              onResumeData={setResumeData}
              resumeData={resumeData}
            />
          </div>

          {/* Right Panel - Job Post */}
          <div className="space-y-6">
            <JobPostInput 
              onJobData={setJobData}
              jobData={jobData}
            />
          </div>
        </div>

        {/* Results Section */}
        {resumeData && jobData && (
          <div className="mt-8">
            <MatchResults 
              resumeData={resumeData}
              jobData={jobData}
              onMatchResults={setMatchResults}
              matchResults={matchResults}
            />
          </div>
        )}
      </main>
    </div>
  )
}

export default App
