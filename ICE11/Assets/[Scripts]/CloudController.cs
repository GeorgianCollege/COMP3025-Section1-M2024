using UnityEngine;

public class CloudController : MonoBehaviour
{
    [Header("Movement Properties")]
    public float minVertical;
    public float maxHorizontal;
    public float minHorizontal;
    public float maxOffscreenVertical;
    public float minOffscreenVertical;
    [Range(5.0f, 10.0f)]
    public float maxVerticalSpeed;
    [Range(5.0f, 10.0f)]
    public float minVerticalSpeed;
    [Range(-2.0f, 2.0f)]
    public float maxHorizontalSpeed;
    [Range(-2.0f, 2.0f)]
    public float minHorizontalSpeed;
    public float horizontalSpeed;
    public float verticalSpeed;

    void Start()
    {
        ResetGameObject();
    }

    void Update()
    {
        Move();
        CheckBounds();
    }

    void ResetGameObject()
    {
        var randomXPosition = Random.Range(minHorizontal, maxHorizontal);
        var randomYPosition = Random.Range(minOffscreenVertical, maxOffscreenVertical);
        horizontalSpeed = Random.Range(minHorizontalSpeed, maxHorizontalSpeed);
        verticalSpeed = Random.Range(minVerticalSpeed, maxVerticalSpeed);
        transform.position = new Vector3(randomXPosition, randomYPosition, 0.0f);
    }

    void Move()
    {
        transform.position += new Vector3(-horizontalSpeed * Time.deltaTime, -verticalSpeed * Time.deltaTime, 0.0f);
    }

    void CheckBounds()
    {
        if (transform.position.y <= minVertical)
        {
            ResetGameObject();
        }
    }
}
