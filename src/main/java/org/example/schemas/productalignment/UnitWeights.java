package org.example.schemas.productalignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks unit weights for carriers, channels, and programs",
        name = "align_unit_weights",
        tags = {"unit", "weights", "carrier", "channel", "program"},
        type = ""
)
@Component
public class UnitWeights {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "channel")
    private String channel;

    @KdbColumn(name = "program_id")
    private Integer programId;
}
