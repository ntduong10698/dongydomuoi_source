package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Part;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.PartDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageJson {
    private Part part;
    private List<PartDetail> partDetails;
}
