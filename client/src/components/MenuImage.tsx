import { socialApi } from "../redux/extraReducers/socialApi";
import { useAppDispatch } from "../redux/hook";
import '../styles/menuimage.scss';

export function MenuImage({ id, token, options }: { id: number, token: string, options: 'image' | 'comment' }) {
    const dispatch = useAppDispatch();
    const deleteData = () => {
        if (confirm('Desea eliminar '+ options)) {
            if (options === 'image')
                dispatch(socialApi.deleteImageById({ token, id }));
            else
                dispatch(socialApi.deleteCommentById({ token, id }));
        }
    }
    return (
        <div className="menuimage">
            <button onClick={deleteData}>Eliminar</button>
        </div>
    );
}
