import React, { useState } from 'react'
import axios from 'axios'

const JobPostInput = ({ onJobData, jobData }) => {
  const [loading, setLoading] = useState(false)
  const [jobText, setJobText] = useState('')

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)

    try {
      const requestData = {
        text: jobText,
      }

      const response = await axios.post('/api/job/fetch', requestData)
      onJobData(response.data)
    } catch (error) {
      console.error('Error fetching job data:', error)
      alert('Error fetching job data. Please try again.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="bg-white rounded-lg shadow-sm border p-6">
      <h2 className="text-xl font-semibold text-gray-900 mb-6">
        Job Description
      </h2>

      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="job-text" className="block text-sm font-medium text-gray-700 mb-2">
            Job Description Text
          </label>
          <textarea
            id="job-text"
            rows={12}
            className="w-full border border-gray-300 rounded-md px-3 py-2 text-sm focus:ring-primary-500 focus:border-primary-500"
            placeholder="Paste the job description here..."
            value={jobText}
            onChange={(e) => setJobText(e.target.value)}
            required
          />
        </div>

        <button
          type="submit"
          disabled={loading || !jobText.trim()}
          className="mt-4 w-full bg-primary-600 text-white py-2 px-4 rounded-md hover:bg-primary-700 disabled:opacity-50 disabled:cursor-not-allowed"
        >
          {loading ? 'Processing...' : 'Analyze Job Description'}
        </button>
      </form>

      {jobData && (
        <div className="border-t pt-6">
          <div className="mb-4">
            <h3 className="text-lg font-medium text-gray-900">{jobData.title}</h3>
            <p className="text-sm text-gray-600">{jobData.company}</p>
          </div>
          
          {jobData.skills && jobData.skills.length > 0 && (
            <div className="mb-4">
              <h4 className="text-sm font-medium text-gray-900 mb-2">Required Skills</h4>
              <div className="flex flex-wrap gap-2">
                {jobData.skills.map((skill, index) => (
                  <span
                    key={index}
                    className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800"
                  >
                    {skill}
                  </span>
                ))}
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  )
}

export default JobPostInput
