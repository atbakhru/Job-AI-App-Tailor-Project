import React, { useState, useEffect } from 'react'
import axios from 'axios'

const MatchResults = ({ resumeData, jobData, onMatchResults, matchResults }) => {
  const [loading, setLoading] = useState(false)

  const generateMatch = async () => {
    setLoading(true)
    try {
      // First, create the match
      const matchResponse = await axios.post('/api/match', {
        resumeId: resumeData.resumeId,
        jobId: jobData.jobId,
      })

      // Then, generate AI tailored content
      const aiResponse = await axios.post('/api/ai/tailor', {
        matchId: matchResponse.data.id,
      })

      const combinedResults = {
        ...matchResponse.data,
        aiOutput: aiResponse.data,
      }

      onMatchResults(combinedResults)
    } catch (error) {
      console.error('Error generating match:', error)
      alert('Error generating match results. Please try again.')
    } finally {
      setLoading(false)
    }
  }

  const copyToClipboard = (text) => {
    navigator.clipboard.writeText(text).then(() => {
      // You could add a toast notification here
      console.log('Copied to clipboard')
    })
  }

  return (
    <div className="bg-white rounded-lg shadow-sm border p-6">
      <div className="flex items-center justify-between mb-6">
        <h2 className="text-xl font-semibold text-gray-900">Match Results</h2>
        <button
          onClick={generateMatch}
          disabled={loading}
          className="bg-primary-600 text-white px-4 py-2 rounded-md hover:bg-primary-700 disabled:opacity-50 disabled:cursor-not-allowed"
        >
          {loading ? 'Analyzing...' : 'Generate Analysis'}
        </button>
      </div>

      {matchResults && (
        <div className="space-y-8">
          {/* Match Score */}
          <div className="bg-gray-50 rounded-lg p-4">
            <div className="flex items-center justify-between mb-2">
              <h3 className="text-lg font-medium text-gray-900">Match Score</h3>
              <span className={`text-2xl font-bold ${
                matchResults.score >= 80 ? 'text-green-600' :
                matchResults.score >= 60 ? 'text-yellow-600' : 'text-red-600'
              }`}>
                {matchResults.score}%
              </span>
            </div>
            <div className="w-full bg-gray-200 rounded-full h-2">
              <div
                className={`h-2 rounded-full ${
                  matchResults.score >= 80 ? 'bg-green-600' :
                  matchResults.score >= 60 ? 'bg-yellow-600' : 'bg-red-600'
                }`}
                style={{ width: `${matchResults.score}%` }}
              ></div>
            </div>
          </div>

          {/* Missing Skills */}
          {matchResults.missingSkills && matchResults.missingSkills.length > 0 && (
            <div>
              <h3 className="text-lg font-medium text-gray-900 mb-3">Missing Skills</h3>
              <div className="flex flex-wrap gap-2">
                {matchResults.missingSkills.map((skill, index) => (
                  <span
                    key={index}
                    className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-red-100 text-red-800"
                  >
                    {skill}
                  </span>
                ))}
              </div>
            </div>
          )}

          {/* AI Generated Content */}
          {matchResults.aiOutput && (
            <div className="space-y-6">
              {/* Tailored Bullets */}
              {matchResults.aiOutput.tailoredBullets && (
                <div>
                  <div className="flex items-center justify-between mb-3">
                    <h3 className="text-lg font-medium text-gray-900">Tailored Resume Bullets</h3>
                    <button
                      onClick={() => copyToClipboard(matchResults.aiOutput.tailoredBullets.join('\n'))}
                      className="text-sm text-primary-600 hover:text-primary-700"
                    >
                      Copy All
                    </button>
                  </div>
                  <div className="space-y-2">
                    {matchResults.aiOutput.tailoredBullets.map((bullet, index) => (
                      <div key={index} className="bg-blue-50 p-3 rounded-md border-l-4 border-blue-500">
                        <p className="text-sm text-gray-800">{bullet}</p>
                        <button
                          onClick={() => copyToClipboard(bullet)}
                          className="mt-1 text-xs text-blue-600 hover:text-blue-700"
                        >
                          Copy
                        </button>
                      </div>
                    ))}
                  </div>
                </div>
              )}

              {/* Cover Letter */}
              {matchResults.aiOutput.coverLetter && (
                <div>
                  <div className="flex items-center justify-between mb-3">
                    <h3 className="text-lg font-medium text-gray-900">Cover Letter Paragraph</h3>
                    <button
                      onClick={() => copyToClipboard(matchResults.aiOutput.coverLetter)}
                      className="text-sm text-primary-600 hover:text-primary-700"
                    >
                      Copy
                    </button>
                  </div>
                  <div className="bg-green-50 p-4 rounded-md border-l-4 border-green-500">
                    <p className="text-sm text-gray-800">{matchResults.aiOutput.coverLetter}</p>
                  </div>
                </div>
              )}

              {/* Gap Analysis */}
              {matchResults.aiOutput.gaps && (
                <div>
                  <h3 className="text-lg font-medium text-gray-900 mb-3">Gap Analysis</h3>
                  <div className="bg-yellow-50 p-4 rounded-md border-l-4 border-yellow-500">
                    <p className="text-sm text-gray-800">{matchResults.aiOutput.gaps}</p>
                  </div>
                </div>
              )}

              {/* Interview Questions */}
              {matchResults.aiOutput.interviewQuestions && (
                <div>
                  <h3 className="text-lg font-medium text-gray-900 mb-3">Interview Questions</h3>
                  <div className="space-y-2">
                    {matchResults.aiOutput.interviewQuestions.map((question, index) => (
                      <div key={index} className="bg-purple-50 p-3 rounded-md">
                        <p className="text-sm text-gray-800 font-medium">Q{index + 1}: {question}</p>
                      </div>
                    ))}
                  </div>
                </div>
              )}

              {/* STAR Prompts */}
              {matchResults.aiOutput.starPrompts && (
                <div>
                  <h3 className="text-lg font-medium text-gray-900 mb-3">STAR Story Prompts</h3>
                  <div className="space-y-2">
                    {matchResults.aiOutput.starPrompts.map((prompt, index) => (
                      <div key={index} className="bg-indigo-50 p-3 rounded-md">
                        <p className="text-sm text-gray-800">{prompt}</p>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          )}
        </div>
      )}
    </div>
  )
}

export default MatchResults
