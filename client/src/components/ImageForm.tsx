

import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { socialApi } from "../redux/extraReducers/socialApi";

export function ImageForm() {
    const [description, setDescription] = React.useState('');
    const [mainFile, setMainFail] = React.useState<File | null>(null);
    const [preUrl, setPreUrl] = React.useState('');
    const dispatch = useAppDispatch();
    const userstate = useAppSelector(state => state.user);
    const socialstate = useAppSelector(state => state.social);
    React.useEffect(() => {
        if (mainFile != null) {
            const reader = new FileReader();
            reader.onload = (e) => {
                const u = e.target?.result ? e.target.result : '';
                setPreUrl(u.toString());
            }
            reader.readAsDataURL(mainFile);
        }else{
            setPreUrl('');
        }
    }, [mainFile]);

    const submit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const formdata = new FormData();
        if (mainFile != null) {
            formdata.append('description', description);
            formdata.append('image', mainFile);
            dispatch(socialApi.saveNewImage({token:userstate.token, formdata}));
            setMainFail(null);
            setDescription('');
        }
    }

    return (
        <form className="image_form" onSubmit={submit}>
            <h2>Nueva imagen</h2>
            <label htmlFor="descriptionimage">Descripcion</label>
            <input
                type="text"
                id="descriptionimage"
                value={description}
                onChange={e => setDescription(e.target.value)}
            />
            <label htmlFor="imagefile">Imagen</label>
            <input
                type="file"
                id="imagefile"
                accept="image/*"
                onChange={e => {
                    const f = e.target.files ? e.target.files[0] : null;
                    if (f != null) {
                        setMainFail(f);
                    }
                }}
            />
            {preUrl.trim() ? <img src={preUrl} alt="" /> : null}
            {socialstate.message?<p>{socialstate.message}</p>:null}
            <button type='submit'>Publicar</button>
        </form>
    );
}
