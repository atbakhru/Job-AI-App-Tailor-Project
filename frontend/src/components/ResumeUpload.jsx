import React, { useState } from 'react'
import axios from 'axios'

const ResumeUpload = ({ onResumeData, resumeData }) => {
  const [loading, setLoading] = useState(false)
  const [uploadMethod, setUploadMethod] = useState('text') // 'text' or 'file'
  const [resumeText, setResumeText] = useState('')
  const [file, setFile] = useState(null)

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)

    try {
      const formData = new FormData()
      formData.append('userId', '1') // Mock user ID for now
      
      if (uploadMethod === 'file' && file) {
        formData.append('file', file)
      } else if (uploadMethod === 'text' && resumeText.trim()) {
        formData.append('text', resumeText)
      }

      const response = await axios.post('/api/resume/parse', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      })

      onResumeData(response.data)
    } catch (error) {
      console.error('Error parsing resume:', error)
      alert('Error parsing resume. Please try again.')
    } finally {
      setLoading(false)
    }
  }

  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0]
    setFile(selectedFile)
  }

  return (
    <div className="bg-white rounded-lg shadow-sm border p-6">
      <h2 className="text-xl font-semibold text-gray-900 mb-6">
        Upload Resume
      </h2>

      <div className="mb-6">
        <div className="flex space-x-4 mb-4">
          <button
            type="button"
            onClick={() => setUploadMethod('text')}
            className={`px-4 py-2 rounded-md text-sm font-medium ${
              uploadMethod === 'text'
                ? 'bg-primary-600 text-white'
                : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
            }`}
          >
            Paste Text
          </button>
          <button
            type="button"
            onClick={() => setUploadMethod('file')}
            className={`px-4 py-2 rounded-md text-sm font-medium ${
              uploadMethod === 'file'
                ? 'bg-primary-600 text-white'
                : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
            }`}
          >
            Upload File
          </button>
        </div>

        <form onSubmit={handleSubmit}>
          {uploadMethod === 'text' ? (
            <div>
              <label htmlFor="resume-text" className="sr-only">
                Resume Text
              </label>
              <textarea
                id="resume-text"
                rows={12}
                className="w-full border border-gray-300 rounded-md px-3 py-2 text-sm focus:ring-primary-500 focus:border-primary-500"
                placeholder="Paste your resume text here..."
                value={resumeText}
                onChange={(e) => setResumeText(e.target.value)}
                required
              />
            </div>
          ) : (
            <div>
              <label htmlFor="resume-file" className="sr-only">
                Resume File
              </label>
              <input
                type="file"
                id="resume-file"
                accept=".pdf,.doc,.docx,.txt"
                onChange={handleFileChange}
                className="w-full border border-gray-300 rounded-md px-3 py-2 text-sm focus:ring-primary-500 focus:border-primary-500"
                required
              />
              <p className="mt-2 text-xs text-gray-500">
                Supports PDF, DOC, DOCX, and TXT files
              </p>
            </div>
          )}

          <button
            type="submit"
            disabled={loading || (uploadMethod === 'text' ? !resumeText.trim() : !file)}
            className="mt-4 w-full bg-primary-600 text-white py-2 px-4 rounded-md hover:bg-primary-700 disabled:opacity-50 disabled:cursor-not-allowed"
          >
            {loading ? 'Processing...' : 'Parse Resume'}
          </button>
        </form>
      </div>

      {resumeData && (
        <div className="border-t pt-6">
          <h3 className="text-lg font-medium text-gray-900 mb-3">
            Detected Skills
          </h3>
          <div className="flex flex-wrap gap-2">
            {resumeData.extractedSkills?.map((skill, index) => (
              <span
                key={index}
                className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-primary-100 text-primary-800"
              >
                {skill}
              </span>
            ))}
          </div>
        </div>
      )}
    </div>
  )
}

export default ResumeUpload
