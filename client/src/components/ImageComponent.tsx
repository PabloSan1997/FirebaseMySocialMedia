

import React from "react";

export function ImageComponent({urlImage, user}:ImagenInterface) {
  return (
    <div className="area_image">
        <img src={urlImage} alt={user.username} />
    </div>
  );
}
