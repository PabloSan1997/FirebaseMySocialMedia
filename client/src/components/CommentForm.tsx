
import '../styles/comment_form.scss';
import React from "react";
import { useAppDispatch } from "../redux/hook";
import { socialApi } from "../redux/extraReducers/socialApi";

export function CommentForm({ idImage, token }: { idImage: number, token: string }) {
    const [comment, setComment] = React.useState('');
    const dispatch = useAppDispatch();
    const submit = (e:React.FormEvent<HTMLFormElement>) =>{
        e.preventDefault();
        dispatch(socialApi.addComment({token, idImage, comment}));
        setComment('');
    }
    return (
        <form className="comment_form" onSubmit={submit}>
            <label htmlFor="newcomment">Nuevo Comentario</label>
            <textarea
                placeholder="Escribir..."
                className='textpadding'
                value={comment}
                onChange={e => setComment(e.target.value)}
            />
            <button type='submit'>Comentar</button>
        </form>
    );
}


