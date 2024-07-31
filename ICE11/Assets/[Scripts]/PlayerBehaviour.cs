using UnityEngine;

public class PlayerBehaviour : MonoBehaviour
{
    public float max;
    public float min;
    public float horizontalSpeed;
    public float verticalPosition;

    void Update()
    {
        Move();
        CheckBounds();
    }

    void Move()
    {
        // if there is at least one touch on the screen
        if (Input.touchCount > 0)
        {
            // gets input touches from screen space in pixels
            var x = Input.touches[0].position.x;

            // convert screen space to world space (unity units)
            var horizontalPosition = Camera.main.ScreenToWorldPoint(new Vector3(x, 0.0f, 0.0f)).x;

            //var x = Input.GetAxisRaw("Horizontal") * horizontalSpeed * Time.deltaTime;
            transform.position = new Vector3(horizontalPosition, verticalPosition, 0.0f);
        }
    }

    void CheckBounds()
    {
        if (transform.position.x <= min)
        {
            transform.position = new Vector3(min, verticalPosition, 0.0f);
        }
        else if (transform.position.x >= max)
        {
            transform.position = new Vector3(max, verticalPosition, 0.0f);
        }
    }

}
