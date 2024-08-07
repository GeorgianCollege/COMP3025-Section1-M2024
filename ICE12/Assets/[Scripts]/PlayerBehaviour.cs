using UnityEngine;

public class PlayerBehaviour : MonoBehaviour
{
    [Header("Movement Properties")]
    public Boundary boundary;
    public float verticalPosition;
    [Header("Sound FX")]
    public AudioSource yaySound;
    public AudioSource thunderSound;

    public GameController gameController;

    void Start()
    {
        gameController = GameObject.Find("GameController").GetComponent<GameController>();
    }


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
        if (transform.position.x <= boundary.min)
        {
            transform.position = new Vector3(boundary.min, verticalPosition, 0.0f);
        }
        else if (transform.position.x >= boundary.max)
        {
            transform.position = new Vector3(boundary.max, verticalPosition, 0.0f);
        }
    }

    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.CompareTag("Island"))
        {
            yaySound.Play();
            gameController.AddScore(100);
        }
        else if (other.gameObject.CompareTag("Cloud"))
        {
            thunderSound.Play();
            gameController.LoseLife();
        }
    }

}
