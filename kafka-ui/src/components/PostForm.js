import React, { useEffect, useState } from 'react'
import axios from 'axios'

function PostForm() {
    const [senderId, setSenderId] = useState("")
    const [content, setContent] = useState("")
    const [isMessageSent, setIsMessageSent] = useState(false)
    const [response, setResponse] = useState([])

    useEffect(() => {
        if (isMessageSent) {
            axios.get('http://localhost:8080/producer/messages')
                .then(res => {
                    setResponse([...response, { senderId: res.data.senderId, content: res.data.content }])

                }).catch(error => {
                    console.log(error)
                })
        }
        setIsMessageSent(false)
    }, [isMessageSent])

    const handleSubmit = (e) => {
        e.preventDefault()

        axios.post('http://localhost:8080/producer/messages', { senderId, content })
            .then(response => {
                setIsMessageSent(true)
            }).catch(error => {
                console.log(error)
            })
    }

    return (
        <div className='container'>
            <form onSubmit={handleSubmit}>
                <div className='field-container'>
                    <div className='input-field'>
                        <label htmlFor="senderId">İsim</label>
                        <input id="senderId" type="text" name='senderId' value={senderId} onChange={(e) => setSenderId(e.target.value)} />
                    </div>
                    <div className='input-field'>
                        <label htmlFor="content">Mesaj</label>
                        <input id="content" type="text" name='content' value={content} onChange={(e) => setContent(e.target.value)} />
                    </div>
                </div>
                <button type='submit'>Submit</button>

            </form>
            {response.map((res, index) => {
                return (
                    <div key={index}>
                        <p>
                            İsim: {res.senderId}
                        </p>
                        <p>
                            Mesaj: {res.content}
                        </p>
                    </div>
                )
            })}

        </div>
    )
}


export default PostForm